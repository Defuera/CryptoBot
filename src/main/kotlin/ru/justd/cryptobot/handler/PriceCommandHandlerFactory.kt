package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade

class PriceCommandHandlerFactory(private val command: String) : CommandHandlerFactory<PriceCommandHandler> {

    private lateinit var exchangeFacade: ExchangeFacade

    fun setExchangeFacade(exchangeFacade: ExchangeFacade): PriceCommandHandlerFactory {
        this.exchangeFacade = exchangeFacade
        return this
    }

    override fun create(): PriceCommandHandler { //todo for some reason here's a warning if PriceCommandHandler is internal
        return PriceCommandHandler(exchangeFacade, command)
    }
}