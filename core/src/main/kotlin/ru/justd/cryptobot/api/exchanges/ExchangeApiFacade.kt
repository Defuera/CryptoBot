package ru.justd.cryptobot.api.exchanges

interface ExchangeApiFacade {

    fun getRate(base: String?, target: String? = null, exchangeApiCode: String? = null): RateResponse

}