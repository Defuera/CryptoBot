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
            "${rate.base} price is ${rate.amount} ${rate.currency}"
        } catch (error: ExchangeNotSupported) {
            "${error.exchange} exchange not supported" //todo log to be aware what exchanges customers are waiting the most, localize
        } catch (error: RequestFailed) {
            error.message
        }
    }


    //region for tests only
    //todo find the way to hide these methods

    fun getBaseCode() = base

    fun getTargetCode() = target

    fun getExchange() = exchange

    //endregion

}