package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.exchanges.exceptions.RequestFailed

class PriceCommandHandler constructor(
        private val exchangeFacade: ExchangeFacade,
        private val base: String?,
        private val target: String?,
        private val exchange: String?
) : CommandHandler {

    override fun responseMessage(): String {
        println("PriceCommandHandler#responseMessage $base $target $exchange")
        return try {
            val rate = exchangeFacade.getRate(base, target, exchange)
            val exchangeInfo = if (exchange == null) "" else "(via $exchange)"
            "${rate.base} price is ${rate.amount} ${rate.target} $exchangeInfo" //todo localize
        } catch (error: ExchangeNotSupported) {
            "${error.exchange} exchange not supported" //todo log to be aware what exchanges customers are waiting the most, localize
        } catch (error: RequestFailed) {
            error.message
        }
    }

}