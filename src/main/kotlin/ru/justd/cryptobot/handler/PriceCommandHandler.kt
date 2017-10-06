package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RequestFailedException

class PriceCommandHandler(
        private val exchangeFacade: ExchangeFacade,
        private val currencyCode: String
) : CommandHandler {

    override fun responseMessage(): String {
        return try {
            val rate = exchangeFacade.getRate(currencyCode)
            "${rate.base} price is ${rate.amount} ${rate.currency}"
        } catch (error: RequestFailedException) {
            error.message
        }
    }

}