package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand

internal class CommandHandlerFacadeImpl(
        private val factories: MutableList<CommandHandlerFactory<*>>
) : CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    override fun handle(channelId: String, request: String): String {
        val factory = factories.find { it.canHandle(request) } ?: throw InvalidCommand("Command `$request` not supported ")
        val handler = factory.create(channelId, request)

        return handler
                .responseMessage()
                .text
    }

    override fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>) {
        factories.add(factory)
    }


}