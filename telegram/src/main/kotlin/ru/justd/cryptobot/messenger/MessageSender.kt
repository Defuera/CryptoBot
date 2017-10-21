package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.Callback
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import com.pengrad.telegrambot.model.request.Keyboard
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.SendResponse
import ru.justd.cryptobot.Bullshit
import ru.justd.cryptobot.adapter.MessageAdapter
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messanger.model.OutgoingMessage
import ru.justd.cryptobot.messenger.model.AnswerCase
import ru.justd.cryptobot.messenger.model.Message
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

        val sendMessageRequest = createSendMessageRequest(
                chatId,
                MessageAdapter().map(outgoingMessage)
        )

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

    private fun createSendMessageRequest(chatId: Long, message: Message): SendMessage {
        val request = SendMessage(chatId, formatMessageText(message.text))

        val keyboard = message.answers
        if (keyboard != null) {
            val telegramKeyboard = mapToTelegramKeyboard(keyboard)
            request.replyMarkup(telegramKeyboard)
        }

        return request.parseMode(ParseMode.Markdown)
    }

    private fun mapToTelegramKeyboard(keyboard: Array<Array<AnswerCase>>): Keyboard =
            InlineKeyboardMarkup(
                    *keyboard
                            .map {
                                it.map {
                                    InlineKeyboardButton(it.text).callbackData("some shitty data")
                                }.toTypedArray()
                            }
                            .toTypedArray()
            )

    private fun formatMessageText(message: String) =
            "*${Bullshit.INSTANCE_ID}*\n_thread: ${Thread.currentThread().name}_\n\n$message"

}