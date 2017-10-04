package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.RequestFailedException

internal class PriceCommandHandler private constructor(private val currencyCode: String) : CommandHandler {

//    private val exchange = ExchangeFacade() //todo how do I get it here???????

    companion object {
        fun newInstance(command: String): CommandHandler {
            return PriceCommandHandler(command.takeLast(3)) //todo magic number
        }
    }

    override fun responseMessage(): String {
        return try {
            val rate = RateResponse(.0, "", "")//exchange.getRate(currencyCode)
            "${rate.base} price is ${rate.amount} ${rate.currency}"
        } catch (error: RequestFailedException) {
            error.message
        }
    }

}