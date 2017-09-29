package ru.justd.cryptobot

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBotAdapter
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity.Type.*
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import java.io.IOException

val BOT_TOKEN = "" //todo store in local gradle file
val bot = TelegramBotAdapter.build(BOT_TOKEN)

fun main(args: Array<String>) {
    println("CryptoBot started")

    bot.setUpdatesListener { updates ->
        updates.forEach { processUpdate(it) }

        CONFIRMED_UPDATES_ALL
    }
}

private fun processUpdate(update: Update) {
    val message = update.message()
    println("message ${message?.entities()?.get(0)?.type() ?: ""}: ${message.text()}")

    val entities = message.entities()
    if (!entities.isEmpty()) {
        entities?.forEach {
            when (it.type()) {
                bot_command -> handleBotCommand(message)
//                mention,
//                hashtag,
//                url,
//                email,
//                bold,
//                italic,
//                code,
//                pre,
//                text_link,
//                text_mention -> println("message type not supported ${it.type()}")
                else -> println("else message type not supported ${it.type()}")
            }
        }
    }
//    else {
//        if (isBotAddedToChannel(message)) {
//            sendMessage(message.chat().pattern(), BotResponse.ABOUT)
//            sendMessage(message.chat().pattern(), BotResponse.HELP)
//        } else {
//            println("message not handled: $message")
//        }
//    }
}

private fun isBotAddedToChannel(message: Message) =
        message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

fun handleBotCommand(message: Message) {
    val command = message.text()
    val chatId = message.chat().id()

    sendMessage(chatId, RequestHandler.valueOf(command))
}

fun sendMessage(chatId: Long, requestHandler: RequestHandler) {
    println("send message...")
    bot.execute(
            SendMessage(chatId, requestHandler.responseMessage()),
            object : Callback<SendMessage, SendResponse> {
                override fun onResponse(request: SendMessage?, response: SendResponse?) {
                    println("response")
                }

                override fun onFailure(request: SendMessage?, e: IOException?) {
                    println("failure")
                }

            })
}