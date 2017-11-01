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

class TelegramMessenger(private val uuid: String) {

    private lateinit var requestProcessor: RequestProcessor

    private val telegramBot: TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType
    private val messageSender = MessageSender(uuid, telegramBot)

    val cryptoCore = CryptoCore()

    init {
        cryptoCore.addCommandHandler(KillCommandHandlerFactory(uuid))
        cryptoCore.setUpdateListener { sendMessage(Reply(it.channelId, it.message)) }
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

            try {
                sendMessage(requestProcessor.process(update))
            } catch (exception: ShutdownException) {
                sendMessage(Reply(exception.channelId, exception.message))
                telegramBot.removeGetUpdatesListener()
                System.exit(0)
            }

        }
    }

    fun sendMessage(reply: Reply) {
        messageSender.sendMessage(toChatId(reply.replyTo), reply)
    }

}