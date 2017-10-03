package ru.justd.cryptobot

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity.Type.*
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandler
import java.io.IOException

val bot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN)

fun main(args: Array<String>) {
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
    if (entities?.isNotEmpty() == true) { //todo is there's a better way
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

//todo is there's better way to do it?
private fun isBotAddedToChannel(message: Message) =
        message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

fun handleBotCommand(message: Message) {
    val requestHandler = Command.findCommandHandler(message.text())
    sendMessage(message.chat().id(), requestHandler)
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