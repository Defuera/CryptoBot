package ru.justd.cryptobot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.di.MainModule
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.messenger.MessageSender
import ru.justd.cryptobot.messenger.Messenger
import ru.justd.cryptobot.messenger.RequestProcessor
import javax.inject.Inject

class TelegramMessenger(private val uuid: String) : Messenger {

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    private lateinit var requestProcessor: RequestProcessor

    private val telegramBot: TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType
    private val messageSender = MessageSender(uuid, telegramBot)

    fun run() {
        println("TelegramMessenger started, id: $uuid")

        DaggerTelegramComponent.builder()
                .mainModule(MainModule(this))
                .build()
                .inject(this)

        requestProcessor = RequestProcessor(commandHandlerFacade)

        telegramBot.setUpdatesListener({ updates ->
            updates.forEach { handleAsync(it) }

            UpdatesListener.CONFIRMED_UPDATES_ALL
        })
    }

    private fun handleAsync(update: Update) {
        launch {
            val chatId = update.message().chat().id()
            sendMessage(toChannelId(chatId), requestProcessor.process(update))
        }
    }


    //region Messenger

    override fun sendMessage(channelId: String, message: String) {
        messageSender.sendMessage(toChatId(channelId), message)
    }

    //endregion


}