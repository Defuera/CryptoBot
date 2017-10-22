package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import java.io.IOException

class MessageSender(
        private val uuid: String,
        private val telegramBot: TelegramBot
) {

    fun sendMessage(
            chatId: Long,
            outgoingMessage: String
    ) {
        println("send response...")

        telegramBot.execute(
                createSendMessageRequest(chatId, outgoingMessage),
                object : Callback<SendMessage, SendResponse> {

                    override fun onResponse(request: SendMessage?, response: SendResponse?) {
                        println("on response sent: ${response?.message()?.text()}")
                    }

                    override fun onFailure(request: SendMessage?, e: IOException?) {
                        println("failure: ${e?.message}")
                    }

                })
    }

    private fun createSendMessageRequest(chatId: Long, message: String): SendMessage {
        val request = SendMessage(chatId, formatMessageText(message))

//        val keyboard = message.answers
//        if (keyboard != null) {
//            val telegramKeyboard = mapToTelegramKeyboard(keyboard)
//            request.replyMarkup(telegramKeyboard)
//        }

        return request.parseMode(ParseMode.Markdown)
    }


    private fun formatMessageText(message: String) =
            "*$uuid*\n_thread: ${Thread.currentThread().name}_\n\n$message"

}