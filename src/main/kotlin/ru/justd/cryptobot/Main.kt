package ru.justd.cryptobot

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity.Type.bot_command
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import ru.justd.cryptobot.di.DaggerMainComponent
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandler
import java.io.IOException
import javax.inject.Inject

class Main {

    @Inject
    lateinit var exchangeFacade: ExchangeFacade

    private val bot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN)
    private val component = DaggerMainComponent.builder().build()

    fun main(args: Array<String>) {
        component.inject(this)
        println("CryptoBot started")

        bot.setUpdatesListener { updates ->
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
            sendMessage(chatId, Command.ABOUT.handler())
            sendMessage(chatId, Command.HELP.handler())
        }
    }

    //todo is there's better way to detect, that bot just been added to a channel/group?
    private fun isBotAddedToChannel(message: Message) =
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

    fun handleBotCommand(message: Message) {
        val commandHandler = Command.findCommandHandler(exchangeFacade, message.text())
        sendMessage(message.chat().id(), commandHandler)
    }

    private fun sendMessage(chatId: Long, commandHandler: CommandHandler) {
        sendMessage(chatId, commandHandler.responseMessage())
    }

    private fun sendMessage(chatId: Long, outcomingMessage: String) {
        println("send message...")
        bot.execute(
                SendMessage(chatId, outcomingMessage),
                object : Callback<SendMessage, SendResponse> {
                    override fun onResponse(request: SendMessage?, response: SendResponse?) {
                        println("response")
                    }

                    override fun onFailure(request: SendMessage?, e: IOException?) {
                        println("failure")
                    }

                })
    }
}