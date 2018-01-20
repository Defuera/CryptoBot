package ru.justd.cryptobot.api.exchanges

import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed

interface ExchangeApi {

    @Throws(RequestFailed::class)
    fun getRate(base: String, target: String): RateResponse

    @Throws(RequestFailed::class)
    fun getCryptoAssets(): Array<String>
}