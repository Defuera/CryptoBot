package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand

interface CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    fun handle(channelId: String, request: String): String

    fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>)

}