package ru.justd.cryptobot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import ru.justd.cryptobot.di.MainModule
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.messenger.MessageReceiver
import ru.justd.cryptobot.messenger.MessageSender
import ru.justd.cryptobot.messenger.Messenger
import ru.justd.cryptobot.messenger.model.OutgoingMessage
import java.util.*
import javax.inject.Inject


fun main(args: Array<String>) {
    TelegramMessenger(UUID.randomUUID().toString()).run()
}

//todo class can be removed once updated to kotlin 1.2. Until then it's used to be able to inject dependencies
class TelegramMessenger(val uuid: String) : Messenger {

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    lateinit var messageReceiver: MessageReceiver

    val telegramBot : TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType
    val messageSender = MessageSender(uuid, telegramBot)

    fun run() {
        println("TelegramMessenger started, id: $uuid")

        DaggerMainComponent
                .builder()
                .mainModule(MainModule(this))
                .build()
                .inject(this)

        messageReceiver = MessageReceiver(commandHandlerFacade)
        //todo killhandler
//        commandHandlerFacade.registerHandler()

        messageReceiver.onUpdateProcessed = { chatId, message ->
            sendMessage(chatId, message)
        }
        telegramBot.setUpdatesListener(messageReceiver)
    }

    override fun sendMessage(channelId: String, message: OutgoingMessage) {
        messageSender.sendMessage(toTelegramId(channelId), message)
    }

    private fun toTelegramId(channelId: String) = channelId.toLong()

    override fun onRequestReceived() {

    }

}