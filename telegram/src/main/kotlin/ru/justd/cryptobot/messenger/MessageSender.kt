package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.LabeledPrice
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.BaseRequest
import com.pengrad.telegrambot.request.EditMessageReplyMarkup
import com.pengrad.telegrambot.request.EditMessageText
import com.pengrad.telegrambot.request.*
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig
import ru.justd.cryptobot.toChatId
import java.io.IOException

class MessageSender(
        private val uuid: String,
        private val telegramBot: TelegramBot
) {

    fun sendMessage(reply: Reply) {
        println("send reply...")

        val invoice = reply.invoice
        if (invoice != null){
            val request = SendInvoice(
                    toChatId(reply.channelId),
                    "Time to pay",
                    reply.text,
                    "test_payload",
                    "361519591:TEST:c6be759042024139d05cf807d2cecb80",
                    "test_payload",
                    invoice.fiatCode,
                    LabeledPrice("rur ${invoice.price}",  invoice.price.toInt())
            )
            executeRequest(request)
        } else {

            val request = SendMessage(reply.channelId, formatMessageText(reply.text))
            executeRequest(request)
            if (KeyboardAdapter.hasOptions(reply)) {
                request.replyMarkup(KeyboardAdapter.createKeyboard(reply))
            }

            request.parseMode(ParseMode.Markdown)
            executeRequest(request)
        }
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

    private fun executeRequest(request: BaseRequest<*, *>) {
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