package ru.justd.cryptobot.exchanges

interface ExchangeApi {
    fun getRate(cryptoCurrencyCode: String, fiatCurrency: String): RateResponse
}