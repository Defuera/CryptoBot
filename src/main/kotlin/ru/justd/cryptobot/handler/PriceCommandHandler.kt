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

    //todo find the way to hide method, should be used for test purposes only
    fun getCurrencyCode() : String{
        return currencyCode
    }

}