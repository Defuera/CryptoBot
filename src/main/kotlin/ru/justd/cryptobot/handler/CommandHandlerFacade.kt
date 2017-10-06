package ru.justd.cryptobot.handler

import ru.justd.cryptobot.Main
import ru.justd.cryptobot.exchanges.ExchangeFacade
import javax.inject.Inject

class CommandHandlerFacade {

    @Inject
    lateinit var exchange: ExchangeFacade

    init {
        Main.component.inject(this)
    }

    fun createCommandHandler(factory: CommandHandlerFactory<*>): CommandHandler =
            when (factory) {
                is PriceCommandHandlerFactory -> factory
                        .apply { exchangeFacade = exchange }
                        .create()
                is InstantFactory<*> -> factory.create()
                else -> throw IllegalArgumentException("Unhandled factory $factory")
            }

}