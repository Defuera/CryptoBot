package ru.justd.cryptobot.exchanges

interface ExchangeFacade {

    fun getRate(base: String): RateResponse

    fun getRate(base: String, target: String): RateResponse

    fun getRate(base: String, target: String, exchangeApiCode: String): RateResponse

}