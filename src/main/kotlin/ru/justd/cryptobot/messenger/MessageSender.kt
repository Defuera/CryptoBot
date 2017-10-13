package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import ru.justd.cryptobot.Main
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.OutgoingMessage
import java.io.IOException

class MessageSender(
        private val telegramBot: TelegramBot
) {

    fun sendMessage(chatId: Long, commandHandler: CommandHandler) {
        sendMessage(chatId, commandHandler.responseMessage()) { _, response ->
            println("response message: ${response?.message()?.text()}")
        }
    }

    fun sendMessage(
            chatId: Long,
            outgoingMessage: OutgoingMessage,
            onSuccess: (request: SendMessage?, response: SendResponse?) -> Unit
    ) {
        println("send message...")

        val sendMessageRequest = createSendMessageRequest(chatId, outgoingMessage)

        val callback = object : Callback<SendMessage, SendResponse> {

            override fun onResponse(request: SendMessage?, response: SendResponse?) {
                onSuccess.invoke(request, response)
            }

            override fun onFailure(request: SendMessage?, e: IOException?) {
                println("failure: ${e?.message}")
            }

        }

        telegramBot.execute(sendMessageRequest, callback)
    }

    private fun createSendMessageRequest(chatId: Long, message: OutgoingMessage) =
            SendMessage(chatId, formatMessageText(message.text))
                    .parseMode(ParseMode.Markdown)

    private fun formatMessageText(message: String) =
            "*${Main.INSTANCE_ID}*\n_thread: ${Thread.currentThread().name}_\n\n$message"

}