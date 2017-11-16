package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.BaseRequest
import com.pengrad.telegrambot.request.EditMessageReplyMarkup
import com.pengrad.telegrambot.request.EditMessageText
import com.pengrad.telegrambot.request.SendMessage
import ru.justd.cryptobot.BuildConfig
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
        executeRequest(request)
    }

    fun updateMessage(messageId: Int, reply: Reply) {
        println("update message...")

        //update text
        executeRequest(EditMessageText(reply.channelId, messageId, formatMessageText(reply.text))) //todo why EditMessageReplyMarkup do not update text?!

        //update keyboard
        val request = EditMessageReplyMarkup(reply.channelId, messageId, formatMessageText(reply.text))

        if (KeyboardAdapter.hasOptions(reply)) {
            request.replyMarkup(KeyboardAdapter.createKeyboard(reply))
        }

        executeRequest(request)

    }

    private fun executeRequest(request: BaseRequest<*,*>) {
        try {
            telegramBot.execute(request)
        } catch (io: IOException) {
            println("failure: ${io.message}")
        }
    }

    private fun formatMessageText(message: String) =
            if (BuildConfig.IS_DEBUG) {
                "*$uuid*\n_thread: ${Thread.currentThread().name}_\n\n$message"
            } else {
                message
            }

}