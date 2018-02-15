package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.LabeledPrice
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.*
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig
import ru.justd.cryptobot.toChatId
import ru.justd.cryptobot.utils.LogFormatter
import ru.justd.cryptobot.utils.LoggerConfig.LOGGER
import java.io.IOException
import java.util.logging.Level

class MessageSender(
        private val uuid: String,
        private val telegramBot: TelegramBot
) {

    fun sendMessage(reply: Reply) {
        LOGGER.log(Level.INFO, LogFormatter.logReply(reply))

        val request = SendMessage(reply.channelId, formatMessageText(reply.text))

        if (KeyboardAdapter.hasOptions(reply)) {
            request.replyMarkup(KeyboardAdapter.createKeyboard(reply))
        }

        request.parseMode(ParseMode.Markdown)
        executeRequest(request)
    }

    fun updateMessage(messageId: Int, reply: Reply) {
        LOGGER.log(Level.INFO, LogFormatter.logReply(reply))

        val invoice = reply.invoice
        if (invoice != null) {
            val sendInvoice = SendInvoice(
                    toChatId(reply.channelId),
                    invoice.title,
                    reply.text,
                    invoice.payload,
                    BuildConfig.PAYMENTWALL_TOKEN,
                    null, //start_parameter
                    invoice.fiatCode,
                    LabeledPrice(invoice.description, invoice.amount)
            )
            if (invoice.needName){
                sendInvoice.needName(true)
            }

            executeRequest(DeleteMessage(reply.channelId, messageId))
            executeRequest(sendInvoice)
        } else {
            //todo because of updating message two times in a row keyboard blinks, it's really annoying
            //update text
            executeRequest(EditMessageText(reply.channelId, messageId, formatMessageText(reply.text)))

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
            LOGGER.log(Level.INFO, "failed to execute request ${io.message}")
        }
    }

    private fun formatMessageText(message: String) =
            if (BuildConfig.IS_DEBUG) {
                "*$uuid*\n_thread: ${Thread.currentThread().name}_\n\n$message"
            } else {
                message
            }

}