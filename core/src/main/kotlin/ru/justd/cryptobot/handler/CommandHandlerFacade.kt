package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand

interface CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    fun handle(channelId: String, request: String): String

    @Throws(InvalidCommand::class)
    @Deprecated("replace with handle, to do this, tests needs to be rewritten")
    fun createCommandHandler(channelId: String, requestMessage: String): CommandHandler

    fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>)

}