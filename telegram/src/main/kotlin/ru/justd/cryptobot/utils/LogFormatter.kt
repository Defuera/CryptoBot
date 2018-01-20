package ru.justd.cryptobot.utils

import com.pengrad.telegrambot.model.*
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig

object LogFormatter {

    fun logMessageReceived(message: Message): String = "onMessage: ${messageInfo(message)}"

    fun logCallbackQuery(query: CallbackQuery): String = "onCallbackQuery: ${messageInfo(query.message())}  data=${query.data()}"

    fun logPreCheckoutQuery(query: PreCheckoutQuery): String {
        val address = query.orderInfo().name()
        val payload = Serializer.deserialize(query.invoicePayload())
        return "onPreCheckoutQuery: id=$query address=$address payload=$payload"
    }

    fun logSuccessfulPayment(chatId: Long, payment: SuccessfulPayment): String {
        val address = payment.orderInfo().name()
        val paymentChargeId = payment.providerPaymentChargeId()
        val payload = Serializer.deserialize(payment.invoicePayload())

        return "onSuccessfulPayment: " +
                "chatId: $chatId " +
                "address: $address " +
                "paymentChargeId: $paymentChargeId" +
                "payload: $payload"
    }

    fun transferSuccessful(chatId: String, payment: SuccessfulPayment): String {
        val address = payment.orderInfo().name()
        val paymentChargeId = payment.providerPaymentChargeId()
        val payload = Serializer.deserialize(payment.invoicePayload())

        return "onTransferSuccessful: " +
                "chatId: $chatId " +
                "address: $address " +
                "paymentChargeId: $paymentChargeId" +
                "payload: $payload"
    }

    fun transferFailed(chatId: String, payment: SuccessfulPayment, error: TransferFailed): String {
        return "onTransferFailed: " +
                "chatId: $chatId " +
                "address: ${error.address} " +
                "cryptoAsset: ${error.base} " +
                "amount: ${error.amount} " +
                "paymentChargeId: ${payment.providerPaymentChargeId()}" +
                "message: ${error.errorMessage} "
    }

    private fun messageInfo(message: Message): String {
        val chatId = message.chat().id()
        val fromId = message.from().id()
        val isPrivate = message.chat().type() == Chat.Type.Private
        val text = formatText(message.text())

        return "chat=$chatId from=$fromId private=$isPrivate text=\"$text\""
    }

    fun formatText(text: String): String {
        return text
                .replace("\n", "\\n")
                .replace("@${BuildConfig.BOT_NAME}", "")
    }

    fun logReply(reply: Reply): String {
        var baseInfo = "chat=${reply.channelId} text=\"${reply.text}\""
        reply.dialog?.let {
            val optionsText = it.dialogOptions.map { it.name }
            baseInfo += " dialog=[\"${it.callbackLabel}\", options=$optionsText]"
        }
        reply.invoice?.let {
            baseInfo += " invoice=[${it.payload}]"
        }

        return baseInfo
    }

}