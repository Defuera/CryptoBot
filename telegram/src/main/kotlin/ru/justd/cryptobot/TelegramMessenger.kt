package ru.justd.cryptobot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.handler.KillCommandHandlerFactory
import ru.justd.cryptobot.handler.ShutdownException
import ru.justd.cryptobot.messenger.MessageSender
import ru.justd.cryptobot.messenger.RequestProcessor

class TelegramMessenger(private val uuid: String) {

    private lateinit var requestProcessor: RequestProcessor

    private val telegramBot: TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType
    private val messageSender = MessageSender(uuid, telegramBot)

    val cryptoCore = CryptoCore()

    init {
        cryptoCore.addCommandHandler(KillCommandHandlerFactory(uuid))

        cryptoCore.setUpdateListener { sendMessage(it.channelId, it.message) }
    }

    fun run() {
        println("TelegramMessenger started, id: $uuid")

        requestProcessor = RequestProcessor(cryptoCore)

        telegramBot.setUpdatesListener { updates ->
            updates.forEach { handleAsync(it) }

            UpdatesListener.CONFIRMED_UPDATES_ALL
        }
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