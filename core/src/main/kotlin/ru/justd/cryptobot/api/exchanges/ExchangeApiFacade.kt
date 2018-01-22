package ru.justd.cryptobot.api.exchanges

interface ExchangeApiFacade {

    fun getRate(base: String, target: String, exchangeApiCode: String): RateResponse

    fun getCryptoAssets(exchange: String): Array<String>

    fun listExchanges(): Array<String>

}