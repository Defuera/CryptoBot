package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade

class PriceCommandHandlerFactory : CommandHandlerFactory<PriceCommandHandler> {

    lateinit var exchangeFacade: ExchangeFacade

    lateinit var message: String

    override fun create(): PriceCommandHandler { //todo for some reason here's a warning if PriceCommandHandler is internal
        return PriceCommandHandler(exchangeFacade, retrieveCurrencyCode(message))
    }

    private fun retrieveCurrencyCode(incomingMessage: String): String {
        return incomingMessage
                .replace(Command.PRICE.scheme, "")
                .trim()
                .split(" ")
                .get(0)
    }

}