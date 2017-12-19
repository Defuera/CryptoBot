package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.LabeledPrice
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.*
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig
import ru.justd.cryptobot.toChatId
import utils.ShiffrLogger
import java.io.IOException

private const val TAG = "MessageSender"

class MessageSender(
        private val uuid: String,
        private val telegramBot: TelegramBot
) {

    fun sendMessage(reply: Reply) {
        val request = SendMessage(reply.channelId, formatMessageText(reply.text))
        executeRequest(request)
        if (KeyboardAdapter.hasOptions(reply)) {
            request.replyMarkup(KeyboardAdapter.createKeyboard(reply))
        }

        request.parseMode(ParseMode.Markdown)

        ShiffrLogger.log(TAG, "reply: $reply")
        executeRequest(request)
    }

    fun updateMessage(messageId: Int, reply: Reply) {
        ShiffrLogger.log(TAG, "update message: $reply")

        val invoice = reply.invoice
        if (invoice != null) {
            val request = SendInvoice(
                    toChatId(reply.channelId),
                    invoice.title,
                    reply.text,
                    invoice.payload,
                    BuildConfig.PAYMENTWALL_TOKEN,
                    null, //start_parameter
                    invoice.fiatCode,
                    LabeledPrice(invoice.description, invoice.amount)
            )
            request.needName(true)

            executeRequest(request)
        } else {

            //update text
            executeRequest(EditMessageText(reply.channelId, messageId, formatMessageText(reply.text))) //todo why EditMessageReplyMarkup do not update text?!

            //update keyboard
            val request = EditMessageReplyMarkup(reply.channelId, messageId, formatMessageText(reply.text))

            if (KeyboardAdapter.hasOptions(reply)) {
                request.replyMarkup(KeyboardAdapter.createKeyboard(reply))
            }

            executeRequest(request)
        }

    }

    fun confirmPreCheckout(answer: AnswerPreCheckoutQuery) {
        executeRequest(answer)
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