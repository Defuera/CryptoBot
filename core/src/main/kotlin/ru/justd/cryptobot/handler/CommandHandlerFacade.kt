package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.messenger.model.Reply

interface CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    fun handle(inquiry: Inquiry): Reply

    fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>)

}