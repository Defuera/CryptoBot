package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.*
import com.pengrad.telegrambot.request.AnswerPreCheckoutQuery
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig
import ru.justd.cryptobot.toChannelId
import ru.justd.cryptobot.utils.AddressUtils
import ru.justd.cryptobot.utils.LogFormatter
import ru.justd.cryptobot.utils.LoggerConfig.LOGGER
import ru.justd.cryptobot.utils.Serializer
import java.util.logging.Level

class RequestProcessor(
        private val core: CryptoCore,
        private val messageSender: MessageSender
) {

    fun process(update: Update) {
        val message = update.message()

        message?.let {
            LOGGER.log(Level.INFO, LogFormatter.logMessageReceived(it))
            handleMessage(it)
        }

        update.callbackQuery()?.let {
            LOGGER.log(Level.INFO, LogFormatter.logCallbackQuery(it))
            handleCallback(it)
        }

        update.preCheckoutQuery()?.let {
            LOGGER.log(Level.INFO, LogFormatter.logPreCheckoutQuery(it))
            handlePreCheckoutQuery(it)
        }

        message?.successfulPayment()?.let {
            LOGGER.log(Level.INFO, LogFormatter.logSuccessfulPayment(message.chat().id(), it))
            handleSuccessfulPayment(it, message)
        }

    }

    private fun handleMessage(message: Message) {
        val channelId = toChannelId(message.chat().id())
        val entity = message.entities()?.first() //todo what if more than one entity? Should we support it?
        val inquiry = message.text()
        val isPrivate = message.chat().type() == Chat.Type.Private

        val reply = if (entity != null) {
            when (entity.type()) {
                MessageEntity.Type.bot_command -> {
                    handleBotCommand(toChannelId(message.chat().id()), isPrivate, inquiry)
                }
                else -> Reply(channelId, "message type not supported ${entity.type()}")
            }
        } else if (isBotAddedToChannel(message)) {
            //greeting message
            core.handle(Inquiry(channelId, isPrivate, "/start"))
        } else {
            null
        }

        reply?.let { messageSender.sendMessage(it) }
    }

    private fun handleBotCommand(channelId: String, isPrivate: Boolean = false, inquiry: String): Reply {
        val filteredInquiry = inquiry.replace("@${BuildConfig.BOT_NAME}", "")

        return try {
            core.handle(Inquiry(channelId, isPrivate, filteredInquiry))
        } catch (invalidCommand: InvalidCommand) {
            Reply(channelId, invalidCommand.message)
        }
    }

    private fun handleCallback(callbackQuery: CallbackQuery): Reply {
        val message = callbackQuery.message()
        val reply = handleBotCommand(
                toChannelId(message.chat().id()),
                message.chat().type() == Chat.Type.Private,
                callbackQuery.data()
        )

        messageSender.updateMessage(message.messageId(), reply)
        return reply
    }

    private fun handlePreCheckoutQuery(query: PreCheckoutQuery) {
        val address = query.orderInfo().name()
        val base = Serializer.deserialize(query.invoicePayload()).base
        if (AddressUtils.validateAddress(address, base)) {
            messageSender.confirmPreCheckout(AnswerPreCheckoutQuery(query.id()))
        } else {
            messageSender.confirmPreCheckout(AnswerPreCheckoutQuery(query.id(), "Sorry, address you provided looks wrong"))
        }
    }

    private fun handleSuccessfulPayment(payment: SuccessfulPayment, message: Message) {
        val channelId = toChannelId(message.from().id().toLong())
        val address = payment.orderInfo().name()
        val paymentChargeId = payment.providerPaymentChargeId()

        try {
            val reply = core.transferFunds(
                    channelId,
                    address,
                    Serializer.deserialize(payment.invoicePayload())
            )

            LOGGER.log(Level.INFO, LogFormatter.transferSuccessful(channelId, payment))
            messageSender.sendMessage(reply)
        } catch (error: TransferFailed) {
            val failReport = "transfer failed: \n" +
                    "channelId: $channelId\n" +
                    "address: ${error.address}\n" +
                    "cryptoAsset: ${error.base}\n" +
                    "amount: ${error.amount}\n" +
                    "paymentChargeId: $paymentChargeId\n" +
                    "message: ${error.errorMessage}\n"

            LOGGER.log(Level.INFO, LogFormatter.transferFailed(channelId, payment, error))
            messageSender.sendMessage(Reply(BuildConfig.FEEDBACK_CHANNEL_ID, failReport))
        }
    }

    private fun isBotAddedToChannel(message: Message) =
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

}
