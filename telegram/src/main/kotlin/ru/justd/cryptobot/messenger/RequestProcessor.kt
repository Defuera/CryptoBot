package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity
import com.pengrad.telegrambot.model.Update
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.toChannelId

class RequestProcessor(private val commandHandlerFacade: CommandHandlerFacade) {

    fun process(update: Update): String {
        val message = update.message()
        val entity = message.entities()?.first() //todo what if more than one entity? Should we support it?
        return if (entity != null) {
            when (entity.type()) {
                MessageEntity.Type.bot_command -> handleBotCommand(message)
                else -> "message type not supported ${entity.type()}"
            }
        } else {
            "message with no entities not supported"
        }
    }

    private fun handleBotCommand(message: Message): String { //todo cover with integration test
        return try {
            commandHandlerFacade.handle(
                    toChannelId(message.chat().id()),
                    message.text()
            )
        } catch (exception: InvalidCommand) {
            exception.message
        }
    }

    //todo is there's better way to detect, that telegramBot just been added to a channel/group?
    private fun isBotAddedToChannel(message: Message) =
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

}