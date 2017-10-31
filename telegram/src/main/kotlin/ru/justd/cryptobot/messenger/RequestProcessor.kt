package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity
import com.pengrad.telegrambot.model.Update
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.toChannelId

class RequestProcessor(private val cryptoCore: CryptoCore) {

    fun process(update: Update): String {
        val message = update.message()
        val entity = message.entities()?.first() //todo what if more than one entity? Should we support it?
        return if (entity != null) {
            when (entity.type()) {
                MessageEntity.Type.bot_command -> handleBotCommand(message)
                else -> "message type not supported ${entity.type()}"
            }
        } else if (isBotAddedToChannel(message)) {
            //greeting message
            cryptoCore.handle(toChannelId(message.chat().id()), "/help")
        } else {
            "message with no entities not supported"
        }
    }

    private fun handleBotCommand(message: Message): String {
        println("bot command recognized: ${message.text()}")
        return try {
            cryptoCore.handle(
                    toChannelId(message.chat().id()),
                    message.text()
            )
        } catch (invalidCommand: InvalidCommand) {
            invalidCommand.message
        }
    }

    private fun isBotAddedToChannel(message: Message) = //todo handle /start here
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

}
