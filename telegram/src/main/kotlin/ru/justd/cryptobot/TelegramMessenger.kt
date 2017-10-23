package ru.justd.cryptobot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.KillCommandHandlerFactory
import ru.justd.cryptobot.handler.ShutdownException
import ru.justd.cryptobot.messenger.MessageSender
import ru.justd.cryptobot.messenger.RequestProcessor
import ru.justd.cryptobot.publisher.Publisher
import javax.inject.Inject

class TelegramMessenger(private val uuid: String) {

    @Inject
    lateinit var publisher: Publisher //todo if I not inject it it's not instantiated, then publisher is not working

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    private lateinit var requestProcessor: RequestProcessor

    private val telegramBot: TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType
    private val messageSender = MessageSender(uuid, telegramBot)

    init {
        DaggerTelegramComponent.builder()
                .build()
                .inject(this)

        commandHandlerFacade.addCommandHandler(KillCommandHandlerFactory(uuid))

        publisher
                .observeUpdates()
                .subscribe { sendMessage(it.channelId, it.message) }


    }

    fun run() {
        println("TelegramMessenger started, id: $uuid")

        requestProcessor = RequestProcessor(commandHandlerFacade)

        telegramBot.setUpdatesListener({ updates ->
            updates.forEach { handleAsync(it) }

            UpdatesListener.CONFIRMED_UPDATES_ALL
        })
    }

    private fun handleAsync(update: Update) {
        launch {
            val chatId = update.message().chat().id()

            try {
                sendMessage(toChannelId(chatId), requestProcessor.process(update))
            } catch (shutdownException: ShutdownException) {
                sendMessage(toChannelId(chatId), shutdownException.message)
                telegramBot.removeGetUpdatesListener()
                System.exit(0)
            }

        }
    }

    fun sendMessage(channelId: String, message: String) {
        messageSender.sendMessage(toChatId(channelId), message)
    }

}