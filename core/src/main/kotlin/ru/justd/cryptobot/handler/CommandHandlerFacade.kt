package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand

interface CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    fun createCommandHandler(channelId: String, requestMessage: String): CommandHandler

}