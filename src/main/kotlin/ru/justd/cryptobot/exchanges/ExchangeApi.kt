package ru.justd.cryptobot.exchanges

interface ExchangeApi {
    fun getRate(currencyCode: String, fiatCurrency: String): RateResponse
}