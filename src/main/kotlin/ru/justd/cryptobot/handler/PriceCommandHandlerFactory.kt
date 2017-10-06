package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacadeImpl

class PriceCommandHandlerFactory(private val command: String) : CommandHandlerFactory<PriceCommandHandler> {

    private lateinit var exchangeFacade: ExchangeFacadeImpl

    fun setExchangeFacade(exchangeFacade: ExchangeFacadeImpl): PriceCommandHandlerFactory {
        this.exchangeFacade = exchangeFacade
        return this
    }

    override fun create(): PriceCommandHandler { //todo for some reason here's a warning if PriceCommandHandler is internal
        return PriceCommandHandler(exchangeFacade, command)
    }
}