package ru.justd.cryptobot.api.exchanges

interface ExchangeApi {
    fun getRate(base: String, target: String): RateResponse
}