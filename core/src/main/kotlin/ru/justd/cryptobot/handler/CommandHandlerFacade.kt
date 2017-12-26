package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Reply

interface CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    fun handle(channelId: String, request: String, private: Boolean): Reply

    fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>)

}