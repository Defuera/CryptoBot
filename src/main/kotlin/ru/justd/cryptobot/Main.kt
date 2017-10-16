package ru.justd.cryptobot

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity.Type.bot_command
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.di.DaggerMainComponent
import ru.justd.cryptobot.di.MainModule
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.kill.KillCommandHandler
import ru.justd.cryptobot.handler.kill.ShutdownException
import ru.justd.cryptobot.messenger.MessageReceiver
import ru.justd.cryptobot.messenger.MessageSender
import ru.justd.cryptobot.messenger.model.OutgoingMessage
import java.util.*
import javax.inject.Inject


fun main(args: Array<String>) {
    Main().run()
}

class Main { //todo class can be removed once updated to kotlin 1.2. Untill then it's used to be able to inject dependencies

    companion object {
        val INSTANCE_ID = UUID.randomUUID().toString()
    }

    @Inject
    lateinit var telegramBot: TelegramBot

    @Inject
    lateinit var messageReceiver: MessageReceiver

    @Inject
    lateinit var messageSender: MessageSender

    fun run() {
        inject()
        initMessageSender()
        initMessageReceiver()
    }

    private fun inject() {
        DaggerMainComponent
                .builder()
                .build()
                .inject(this)
    }

    private fun initMessageSender() {
        messageReceiver.onProcessListener = { chatId, commandHandler ->
            messageSender.sendMessage(chatId, commandHandler)
        }
    }

    private fun initMessageReceiver() {
        telegramBot.setUpdatesListener { updates ->
            updates.forEach {
                launch { processUpdate(it) }
            }

            CONFIRMED_UPDATES_ALL
        }
    }

    private fun processUpdate(update: Update) {
        try {
            messageReceiver.processUpdate(update)
        } catch (e: ShutdownException) {
            killInstance(update.message().chat().id())
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun killInstance(chatId: Long) {
        messageSender.sendMessage(
                chatId,
                OutgoingMessage(KillCommandHandler.FAREWELL_MESSAGE)
        ) { _, _ ->
            telegramBot.removeGetUpdatesListener()
            System.exit(0)
        }
    }

}