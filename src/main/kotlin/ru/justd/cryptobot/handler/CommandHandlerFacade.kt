package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade

class CommandHandlerFacade(
        private val exchange: ExchangeFacade
) {
    fun createCommandHandler(factory: CommandHandlerFactory<*>): CommandHandler =
            when (factory) {
                is PriceCommandHandlerFactory -> factory
                        .apply { exchangeFacade = exchange }
                        .create()
                is InstantFactory<*> -> factory.create()
                else -> throw IllegalArgumentException("Unhandled factory $factory")
            }

}