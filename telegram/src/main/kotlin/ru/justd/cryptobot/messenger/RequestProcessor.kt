package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.*
import com.pengrad.telegrambot.request.AnswerPreCheckoutQuery
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig
import ru.justd.cryptobot.toChannelId
import utils.ShiffrLogger

class RequestProcessor(
        private val cryptoCore: CryptoCore,
        private val messageSender: MessageSender
) {

    fun process(update: Update) {
        update.message()?.let {
            handleMessage(it)
        }

        update.callbackQuery()?.let {
            handleCallback(it)
        }

        update.preCheckoutQuery()?.let {
            val answer = AnswerPreCheckoutQuery(it.id())
            messageSender.confirmPreCheckout(answer)
            println("precheckout successful")
        }

    }

    private fun handleCallback(callbackQuery: CallbackQuery): Reply {
        val message = callbackQuery.message()
        val reply = handleBotCommand(
                toChannelId(message.chat().id()),
                callbackQuery.data()
        )

        messageSender.updateMessage(message.messageId(), reply)
        return reply
    }

    private fun handleMessage(message: Message) {
        val channelId = toChannelId(message.chat().id())
        val entity = message.entities()?.first() //todo what if more than one entity? Should we support it?
        val inquiry = message.text()
        ShiffrLogger.log("RequestProcessor#handleMessage", "inquiry: $inquiry")

        val reply = if (entity != null) {
            when (entity.type()) {
                MessageEntity.Type.bot_command -> { handleBotCommand(toChannelId(message.chat().id()), inquiry) }
                else -> Reply(channelId, "message type not supported ${entity.type()}")
            }
        } else if (isBotAddedToChannel(message)) {
            //greeting message
            cryptoCore.handle(channelId, "/help")
        } else {
            null
        }

        reply?.let { messageSender.sendMessage(it) }
    }

    private fun handleBotCommand(channelId: String, inquiry: String): Reply {
        val filteredInquiry = inquiry.replace("@${BuildConfig.BOT_NAME}", "")

        return try {
            cryptoCore.handle(channelId, filteredInquiry)
        } catch (invalidCommand: InvalidCommand) {
            Reply(channelId, invalidCommand.message)
        }
    }

    private fun isBotAddedToChannel(message: Message) = //todo handle /start here
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

}
