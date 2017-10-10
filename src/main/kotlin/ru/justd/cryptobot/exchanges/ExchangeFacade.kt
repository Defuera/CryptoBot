package ru.justd.cryptobot.exchanges

interface ExchangeFacade {

    fun getRate(base: String?, target: String? = null, exchangeApiCode: String? = null): RateResponse

}