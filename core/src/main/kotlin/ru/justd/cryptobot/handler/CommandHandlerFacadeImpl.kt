package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.handler.exceptions.UnsupportedCommandHandler
import ru.justd.cryptobot.handler.subscribe.SubscribeFactory
import ru.justd.cryptobot.handler.kill.KillCommandHandlerFactory
import ru.justd.cryptobot.handler.price.PriceCommandHandlerFactory
import ru.justd.cryptobot.persistance.Storage

internal class CommandHandlerFacadeImpl( //todo it seems like this is an object (kotlin singleton), the only problem it cannot be statically initialized, since it get dynamically created dependency. But all the functions are static.. hmm..
        private val exchange: ExchangeFacade,
        private val store: Storage
) : CommandHandlerFacade {

    override fun handle(channelId: String, requestMessage: String): String =
            createCommandHandler(channelId, requestMessage)
                    .responseMessage()
                    .text

    @Throws(InvalidCommand::class)
    override fun createCommandHandler(channelId: String, requestMessage: String): CommandHandler {
        val factory = findCommandHandlerFactory(requestMessage)

        return when (factory) {
            is PriceCommandHandlerFactory -> factory
                    .apply {
                        exchangeFacade = exchange
                        message = requestMessage
                    }
                    .create()
            is KillCommandHandlerFactory -> factory
                    .apply { message = requestMessage }
                    .create()
            is SubscribeFactory -> factory
                    .apply {
                        message = requestMessage
                        this.storage = store
                        this.id = channelId
                    }
                    .create()
            is InstantFactory<*> -> factory.create()
            else -> throw IllegalArgumentException("Unhandled factory $factory")
        }

    }

    private fun findCommandHandlerFactory(incomingMessage: String) = find(incomingMessage)?.factory() ?: InstantFactory(UnsupportedCommandHandler) //todo remove UnsupportedCommandHandler, instead throw InvalidCommand Exception

    private fun find(incomingMessage: String): Command? = Command.values().firstOrNull { matches(it, incomingMessage) }

    private fun matches(command: Command, message: String): Boolean {
        return message.split(" ")[0].toLowerCase() == command.scheme
    }

}