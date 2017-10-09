package ru.justd.cryptobot

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity.Type.bot_command
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import ru.justd.cryptobot.di.DaggerMainComponent
import ru.justd.cryptobot.handler.*
import java.io.IOException
import javax.inject.Inject


fun main(args: Array<String>) {
    Main().run()
}

class Main { //todo class can be removed once updated to kotlin 1.2. Untill then it's used to be able to inject dependencies

    @Inject
    lateinit var telegramBot: TelegramBot

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    fun run() {
        DaggerMainComponent.builder()
                .build()
                .inject(this)

        println("CryptoBot started")

        telegramBot.setUpdatesListener { updates ->
            updates.forEach {
                try {
                    processUpdate(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            CONFIRMED_UPDATES_ALL
        }
    }

    private fun processUpdate(update: Update) {
        val message = update.message()
        println("message ${message?.entities()?.get(0)?.type() ?: ""}: ${message?.text() ?: "null"}")

        val entities = message.entities()
        if (entities?.isNotEmpty() == true) {
            entities.forEach {
                when (it.type()) {
                    bot_command -> handleBotCommand(message)
                    else -> println("else message type not supported ${it.type()}")
                }
            }
        }

        if (isBotAddedToChannel(message)) {
            val chatId = message.chat().id()
            sendMessage(chatId, Command.ABOUT.factory().create())
            sendMessage(chatId, Command.HELP.factory().create())
        }
    }

    //todo is there's better way to detect, that telegramBot just been added to a channel/group?
    private fun isBotAddedToChannel(message: Message) =
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

    private fun handleBotCommand(message: Message) { //todo cover with integration test
        val commandHandler = commandHandlerFacade.createCommandHandler(message.text())
        sendMessage(message.chat().id(), commandHandler)
    }

    private fun sendMessage(chatId: Long, commandHandler: CommandHandler) {
        sendMessage(chatId, commandHandler.responseMessage())
    }

    private fun sendMessage(chatId: Long, outgoingMessage: String) {
        println("send message...")
        telegramBot.execute(
                SendMessage(chatId, outgoingMessage).parseMode(ParseMode.Markdown),
                object : Callback<SendMessage, SendResponse> {
                    override fun onResponse(request: SendMessage?, response: SendResponse?) {
                        println("response message: ${response?.message()?.text()}")
                    }

                    override fun onFailure(request: SendMessage?, e: IOException?) {
                        println("failure: ${e?.message}")
                    }

                })
    }
}