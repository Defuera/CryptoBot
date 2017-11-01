package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Reply

internal class CommandHandlerFacadeImpl(
        private val factories: MutableList<CommandHandlerFactory<*>>
) : CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    override fun handle(channelId: String, request: String): Reply {
        val factory = factories.find { it.canHandle(request) } ?: throw InvalidCommand("Command `$request` not supported ")
        val handler = factory.create(channelId, request)

        return handler.createReply()
    }

    override fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>) {
        factories.add(factory)
    }


}