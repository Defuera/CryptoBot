package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RequestFailedException

internal class PriceCommandHandler private constructor(private val currencyCode: String) : CommandHandler {

    lateinit var exchangeFacade : ExchangeFacade //todo this is sucky way to provide dependency here

    companion object {
        fun newInstance(command: String): PriceCommandHandler {
            return PriceCommandHandler(command.takeLast(3)) //todo magic number
        }
    }

    override fun responseMessage(): String {
        return try {
            val rate = exchangeFacade.getRate(currencyCode)
            "${rate.base} price is ${rate.amount} ${rate.currency}"
        } catch (error: RequestFailedException) {
            error.message
        }
    }

}