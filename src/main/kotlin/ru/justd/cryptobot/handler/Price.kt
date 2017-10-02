package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RequestFailedException

internal class Price private constructor(private val currencyCode: String) : RequestHandler {

    companion object {
        fun newInstance(command: String): RequestHandler {
            return Price(command.takeLast(3)) //todo magic number
        }
    }

    override fun responseMessage(): String {
        return try {
            val rate = exchange.getRate(currencyCode)
            "${rate.base} price is ${rate.amount} ${rate.currency}"
        } catch (error: RequestFailedException) {
            error.message
        }
    }

    private val exchange = ExchangeFacade() //todo inject with dagger based on user preferences?

}