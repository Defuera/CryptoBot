package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade

class CommandHandlerFacadeImpl( //todo it seems like this is an object (kotlin singleton), the only problem it cannot be statically initialized, since it get dynamically created dependency. But all the functions are static.. hmm..
        private val exchange: ExchangeFacade
) : CommandHandlerFacade {

    override fun createCommandHandler(incomingMessage: String): CommandHandler {
        val factory = findCommandHandlerFactory(incomingMessage)

        return when (factory) {
            is PriceCommandHandlerFactory -> factory
                    .apply {
                        exchangeFacade = exchange
                        message = incomingMessage
                    }
                    .create()
            is InstantFactory<*> -> factory.create()
            else -> throw IllegalArgumentException("Unhandled factory $factory")
        }

    }

    private fun findCommandHandlerFactory(incomingMessage: String) = find(incomingMessage)?.factory() ?: InstantFactory(UnsupportedCommandHandler)

    private fun find(incomingMessage: String): Command? = Command.values().firstOrNull { matches(it, incomingMessage) }

    private fun matches(command : Command, message: String): Boolean {
        return message.split(" ")[0] == command.scheme
    }

}