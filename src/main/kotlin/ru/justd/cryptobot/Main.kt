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
import ru.justd.cryptobot.di.MainComponent
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.kill.KillCommandHandler
import ru.justd.cryptobot.handler.kill.ShutdownException
import java.io.IOException
import java.util.*
import javax.inject.Inject


fun main(args: Array<String>) {
    Main().run()
}

//todo class can be removed once updated to kotlin 1.2. Untill then it's used to be able to inject dependencies
//open for tests
open class Main {

    @Inject
    lateinit var telegramBot: TelegramBot

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    fun run() {
        val mainComponent = createComponent()
        mainComponent.inject(this)

        println("CryptoBot started")

        telegramBot.setUpdatesListener { updates ->
            updates.forEach {
                try {
                    processUpdate(it)
                } catch (e: ShutdownException) {
                    killInstance(it.message().chat().id())
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }

            CONFIRMED_UPDATES_ALL
        }
    }

    //open for tests
    open fun createComponent(): MainComponent = DaggerMainComponent.builder().build()

    private fun processUpdate(update: Update) {
        val message = update.message()
        println("message ${message?.entities()?.get(0)?.type() ?: ""}: ${message?.text() ?: "null"}")

        val chatId = message.chat().id()
        val entities = message.entities()
        if (entities?.isNotEmpty() == true) {
            entities.forEach {
                when (it.type()) {
                    bot_command -> sendMessage(chatId, handleBotCommand(message.text()))
                    else -> println("else message type not supported ${it.type()}")
                }
            }
        }

        if (isBotAddedToChannel(message)) {
            sendMessage(chatId, toMessage(Command.ABOUT))
            sendMessage(chatId, toMessage(Command.HELP))
        }
    }

    private fun toMessage(command: Command) = command.factory().create().responseMessage()

    //todo is there's better way to detect, that telegramBot just been added to a channel/group?
    private fun isBotAddedToChannel(message: Message) =
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

    //todo visible for testing purposes only
    fun handleBotCommand(message: String): String {
        return commandHandlerFacade
                .createCommandHandler(message)
                .responseMessage()
    }

    private fun sendMessage(chatId: Long, outgoingMessage: String) {
        sendMessage(chatId, outgoingMessage) { _, response ->
            println("response message: ${response?.message()?.text()}")
        }
    }

    private fun sendMessage(
            chatId: Long,
            outgoingMessage: String,
            onSuccess: (request: SendMessage?, response: SendResponse?) -> Unit
    ) {
        println("send message...")
        telegramBot.execute(
                SendMessage(chatId, createOutgoingMessage(outgoingMessage)).parseMode(ParseMode.Markdown),
                object : Callback<SendMessage, SendResponse> {

                    override fun onResponse(request: SendMessage?, response: SendResponse?) {
                        onSuccess.invoke(request, response)
                    }

                    override fun onFailure(request: SendMessage?, e: IOException?) {
                        println("failure: ${e?.message}")
                    }

                })
    }

    private fun createOutgoingMessage(message: String) =
            "*${BuildConfig.INSTANCE_ID}*\n\n$message"

    private fun killInstance(chatId: Long) {
        sendMessage(chatId, KillCommandHandler.FAREWELL_MESSAGE) { _, _ ->
            telegramBot.removeGetUpdatesListener()
            System.exit(0)
        }
    }

}