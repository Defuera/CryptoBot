package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand

interface CommandHandlerFactory<out T : CommandHandler> {

    @Throws(InvalidCommand::class)
    fun create() : T
}