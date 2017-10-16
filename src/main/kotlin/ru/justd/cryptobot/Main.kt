package ru.justd.cryptobot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.di.DaggerMainComponent
import ru.justd.cryptobot.handler.kill.KillCommandHandler
import ru.justd.cryptobot.handler.kill.ShutdownException
import ru.justd.cryptobot.messenger.MessageReceiver
import ru.justd.cryptobot.messenger.MessageSender
import ru.justd.cryptobot.messenger.model.OutgoingMessage
import java.util.*
import javax.inject.Inject


fun main(args: Array<String>) {
    TelegramCryptAdviser().run()
}

//todo class can be removed once updated to kotlin 1.2. Until then it's used to be able to inject dependencies
class TelegramCryptAdviser {

    companion object {
        val INSTANCE_ID = UUID.randomUUID().toString()
    }

    @Inject
    lateinit var telegramBot: TelegramBot

    @Inject
    lateinit var messageReceiver: MessageReceiver

    @Inject
    lateinit var messageSender: MessageSender

    init {
        val mainComponent = DaggerMainComponent.builder().build()
        mainComponent.inject(this)
    }


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