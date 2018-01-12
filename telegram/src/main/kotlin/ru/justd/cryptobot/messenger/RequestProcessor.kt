package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.*
import com.pengrad.telegrambot.request.AnswerPreCheckoutQuery
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.handler.purchase.PurchaseFacade
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig
import ru.justd.cryptobot.toChannelId
import ru.justd.cryptobot.utils.Serializer
import ru.justd.cryptobot.utils.ShiffrLogger

class RequestProcessor(
        private val cryptoCore: CryptoCore,
        private val messageSender: MessageSender,
        private val purchaseFacade: PurchaseFacade
) {

    fun process(update: Update) {
        val message = update.message()

        message?.let { handleMessage(it) }

        update.callbackQuery()?.let { handleCallback(it) }

        update.preCheckoutQuery()?.let {
            ShiffrLogger.log("RequestProcessor#preCheckoutQuery", "user: ${it.from().id()} ${it.invoicePayload()} ${it.orderInfo()}")
            val address = it.orderInfo().name()
            val base = Serializer.deserialize(it.invoicePayload()).base
            if (purchaseFacade.validateAddress(address, base)) {
                messageSender.confirmPreCheckout(AnswerPreCheckoutQuery(it.id()))
            } else {
                messageSender.confirmPreCheckout(AnswerPreCheckoutQuery(it.id(), "Sorry, address you provided looks wrong"))
            }
        }

        message?.successfulPayment()?.let {
            val channelId = toChannelId(message.from().id().toLong())
            val paymentChargeId = it.providerPaymentChargeId()
            ShiffrLogger.log("RequestProcessor#successfulPayment", "payment_provider_charge_id: $paymentChargeId user: $channelId")
            val address = it.orderInfo().name()

            try {
                val reply = purchaseFacade.transferFunds(
                        channelId,
                        address,
                        Serializer.deserialize(it.invoicePayload())
                )
                messageSender.sendMessage(reply)
            } catch (error: TransferFailed) {
                val failReport = "transfer failed: \n" +
                        "channelId: $channelId\n" +
                        "address: ${error.address}\n" +
                        "base: ${error.base}\n" +
                        "amount: ${error.amount}\n" +
                        "paymentChargeId: $paymentChargeId\n" +
                        "message: ${error.errorMessage}\n"

                ShiffrLogger.log("purchase", failReport)
                messageSender.sendMessage(Reply(BuildConfig.FEEDBACK_CHANNEL_ID, failReport))
            }
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

    private fun handleMessage(message: Message) {
        val channelId = toChannelId(message.chat().id())
        val entity = message.entities()?.first() //todo what if more than one entity? Should we support it?
        val inquiry = message.text()
        val isPrivate = message.chat().type() == Chat.Type.Private
        ShiffrLogger.log("RequestProcessor#handleMessage", "inquiry: $inquiry")

        val reply = if (entity != null) {
            when (entity.type()) {
                MessageEntity.Type.bot_command -> {
                    handleBotCommand(toChannelId(message.chat().id()), isPrivate, inquiry)
                }
                else -> Reply(channelId, "message type not supported ${entity.type()}")
            }
        } else if (isBotAddedToChannel(message)) {
            //greeting message
            cryptoCore.handle(Inquiry(channelId, isPrivate, "/help"))
        } else {
            null
        }

        reply?.let { messageSender.sendMessage(it) }
    }

    private fun handleBotCommand(channelId: String, isPrivate: Boolean = false, inquiry: String): Reply {
        val filteredInquiry = inquiry.replace("@${BuildConfig.BOT_NAME}", "")

        return try {
            cryptoCore.handle(Inquiry(channelId, isPrivate, filteredInquiry))
        } catch (invalidCommand: InvalidCommand) {
            Reply(channelId, invalidCommand.message)
        }
    }

    private fun isBotAddedToChannel(message: Message) = //todo handle /start here
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

}
