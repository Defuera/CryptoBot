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
                mention,
                hashtag,
                url,
                email,
                bold,
                italic,
                code,
                pre,
                text_link,
                text_mention -> println("message type not supported ${it.type()}")
                else -> println("else message type not supported ${it.type()}")
            }
        }
    } else {
        if (isBotAddedToChannel(message)) {
            sendMessage(message.chat().id(), BotResponse.ABOUT)
            sendMessage(message.chat().id(), BotResponse.HELP)
        } else {
            println("message not handled: $message")
        }
    }
}

private fun isBotAddedToChannel(message: Message) =
        message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

fun handleBotCommand(message: Message) {
    val command = message.text().replace("/", "")
    val chatId = message.chat().id()

    sendMessage(chatId, findResponseFor(command))
}

fun sendMessage(chatId: Long, response: BotResponse) {
    println("send message...")
    bot.execute(
            SendMessage(chatId, response.responseMessage),
            object : Callback<SendMessage, SendResponse> {
                override fun onResponse(request: SendMessage?, response: SendResponse?) {
                    println("response")
                }

                override fun onFailure(request: SendMessage?, e: IOException?) {
                    println("failure")
                }

            })
}

fun findResponseFor(command: String) = BotResponse.values().find { it.command == command } ?: BotResponse.UNSUPPORTED_REQUEST

//fun replyTo(command: String) = BotResponse.values().find { it.command == command }?.responseMessage

enum class BotResponse(val command: String?, val responseMessage: String) { //todo use sealed class to differentiate the functionality

    /**
     * Information about available commands
     */
    HELP("help", "no help for you beach"),

    /**
     * Update for your subscriptions
     */
    UPDATE("update", "your update"),

    /**
     * Information of current version of the bot
     */
    ABOUT("about", "v.1, to see list of supported commands type /help"), //todo retrieve version from build.gradle

    UNSUPPORTED_REQUEST(null, "command not recognized");


}