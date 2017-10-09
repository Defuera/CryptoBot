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
        return try {
            val rate = exchangeFacade.getRate(base, target, exchange)
            "${rate.base} price is ${rate.amount} ${rate.target}"
        } catch (error: ExchangeNotSupported) {
            "${error.exchange} exchange not supported" //todo log to be aware what exchanges customers are waiting the most, localize
        } catch (error: RequestFailed) {
            error.message
        }
    }

}