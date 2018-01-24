package ru.justd.cryptobot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.handler.InstantFactory
import ru.justd.cryptobot.handler.KillCommandHandlerFactory
import ru.justd.cryptobot.handler.ShutdownException
import ru.justd.cryptobot.handler.StartHandler
import ru.justd.cryptobot.messenger.MessageSenderImpl
import ru.justd.cryptobot.messenger.RequestProcessor
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.telegram.BuildConfig
import ru.justd.cryptobot.utils.LoggerConfig.LOGGER
import java.util.logging.Level

class TelegramMessenger(private val uuid: String) {

    private lateinit var requestProcessor: RequestProcessor

    private val telegramBot: TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN)
    private val messageSender = MessageSenderImpl(uuid, telegramBot)

    private val cryptoCore = CryptoCore.start(
            BuildConfig.NAME,
            BuildConfig.IS_DEBUG,
            object : FeedbackStorage {
                override fun store(feedback: String) {
                    sendMessage(Reply(BuildConfig.FEEDBACK_CHANNEL_ID, feedback))
                }

            })

    fun run() {
        LOGGER.log(Level.INFO, "TelegramMessenger started, id: $uuid")

        if (BuildConfig.IS_DEBUG) {
            //allows to terminate bot process with telegram command
            cryptoCore.addCommandHandler(KillCommandHandlerFactory(uuid))
        }

        cryptoCore.addCommandHandler(InstantFactory("/start", StartHandler(cryptoCore.analytics)))
        cryptoCore.setUpdateListener { sendMessage(Reply(it.channelId, it.message)) }

        requestProcessor = RequestProcessor(
                cryptoCore,
                messageSender,
                cryptoCore.purchaseFacade
        )

        telegramBot.setUpdatesListener { updates ->
            updates.forEach { handleAsync(it) }

            UpdatesListener.CONFIRMED_UPDATES_ALL
        }

        UpdateNotesManager(messageSender).sendUpdates()
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