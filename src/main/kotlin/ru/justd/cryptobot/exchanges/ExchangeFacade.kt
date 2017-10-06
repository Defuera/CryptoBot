package ru.justd.cryptobot.exchanges

interface ExchangeFacade {

    fun getRate(currencyCode: String): RateResponse
}