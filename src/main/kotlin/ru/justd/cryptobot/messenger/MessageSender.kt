package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import ru.justd.cryptobot.Main
import ru.justd.cryptobot.handler.CommandHandler
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
            "*${Main.INSTANCE_ID}*\n_thread: ${Thread.currentThread().name}_\n\n$message"

}