package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand

internal class CommandHandlerFacadeImpl constructor( //todo it seems like this is an object (kotlin singleton), the only problem it cannot be statically initialized, since it get dynamically created dependency. But all the functions are static.. hmm..
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

    @Throws(InvalidCommand::class)
    override fun createCommandHandler(channelId: String, request: String): CommandHandler {
        throw IllegalArgumentException("Unhandled factory lol")
    }

    override fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>) {
        factories.add(factory)
    }


}