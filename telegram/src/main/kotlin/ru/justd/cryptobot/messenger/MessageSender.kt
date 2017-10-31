package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import com.pengrad.telegrambot.model.request.Keyboard
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
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

        val request = SendMessage(chatId, formatMessageText(outgoingMessage))
//        request.attachKeyboard(arrayOf(
//                arrayOf("test"),
//                arrayOf("case", "lace")
//        ))
        request.parseMode(ParseMode.Markdown)

        try {
            val response = telegramBot.execute(request)
            println("on response sent: ${response?.message()?.text()}")
        } catch (io: IOException) {
            println("failure: ${io.message}")
        }

    }
    private fun formatMessageText(message: String) =
            "*$uuid*\n_thread: ${Thread.currentThread().name}_\n\n$message"

    fun SendMessage.attachKeyboard(keyboard: Array<Array<String>>) {
        val telegramKeyboard = mapToTelegramKeyboard(keyboard)
        this.replyMarkup(telegramKeyboard)
    }

    private fun mapToTelegramKeyboard(keyboard: Array<Array<String>>): Keyboard =
            InlineKeyboardMarkup(
                    *keyboard
                            .map {
                                it.map {
                                    InlineKeyboardButton(it).callbackData("/callback $it")
                                }.toTypedArray()
                            }
                            .toTypedArray()
            )

}