package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.EditMessageReplyMarkup
import com.pengrad.telegrambot.request.SendMessage
import ru.justd.cryptobot.messenger.model.Reply
import java.io.IOException

class MessageSender(
        private val uuid: String,
        private val telegramBot: TelegramBot
) {

    fun sendMessage(reply: Reply) {
        println("send reply...")

        val request = SendMessage(reply.channelId, formatMessageText(reply.text))
        if (KeyboardAdapter.hasOptions(reply)) {
            request.replyMarkup(KeyboardAdapter.createKeyboard(reply))
        }

        request.parseMode(ParseMode.Markdown)

        try {
            val response = telegramBot.execute(request)
            println("on response sent: ${response?.message()?.text()}")
        } catch (io: IOException) {
            println("failure: ${io.message}")
        }

    }

    fun updateMessage(messageId: Int, reply: Reply) {
        println("update message...")

        val request = EditMessageReplyMarkup(reply.channelId, messageId, formatMessageText(reply.text))

        if (KeyboardAdapter.hasOptions(reply)) {
            request.replyMarkup(KeyboardAdapter.createKeyboard(reply))
        }

        try {
            telegramBot.execute(request)
        } catch (io: IOException) {
            println("failure: ${io.message}")
        }

    }

    private fun formatMessageText(message: String) =
            "*$uuid*\n_thread: ${Thread.currentThread().name}_\n\n$message"

}