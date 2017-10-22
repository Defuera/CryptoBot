package ru.justd.cryptobot.exchanges

interface ExchangeApi {
    fun getRate(base: String, target: String): RateResponse
}