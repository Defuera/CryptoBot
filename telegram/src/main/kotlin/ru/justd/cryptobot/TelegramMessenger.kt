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
import ru.justd.cryptobot.messenger.model.Reply

class TelegramMessenger(private val uuid: String) { //todo https://core.telegram.org/bots/faq#how-can-i-make-requests-in-response-to-updates

    private lateinit var requestProcessor: RequestProcessor

    private val telegramBot: TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN)
    private val messageSender = MessageSender(uuid, telegramBot)

    val cryptoCore = CryptoCore.start(BuildConfig.NAME, BuildConfig.IS_DEBUG)

    init {
        if (BuildConfig.IS_DEBUG) {
            cryptoCore.addCommandHandler(KillCommandHandlerFactory(uuid))
        }
        cryptoCore.setUpdateListener { sendMessage(Reply(it.channelId, it.message)) }
    }

    fun run() {
        println("TelegramMessenger started, id: $uuid")

        requestProcessor = RequestProcessor(cryptoCore, messageSender)

        telegramBot.setUpdatesListener { updates ->
            updates.forEach { handleAsync(it) }

            UpdatesListener.CONFIRMED_UPDATES_ALL
        }
    }

    private fun handleAsync(update: Update) {
        launch {

            try {
                requestProcessor.process(update)
            } catch (exception: ShutdownException) {
                sendMessage(Reply(exception.channelId, exception.message))
                telegramBot.removeGetUpdatesListener()
                System.exit(0)
            }

        }
    }

    fun sendMessage(reply: Reply) {
        messageSender.sendMessage(reply)
    }

}