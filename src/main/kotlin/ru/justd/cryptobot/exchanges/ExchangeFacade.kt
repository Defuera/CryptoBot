package ru.justd.cryptobot.exchanges

import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import javax.inject.Named

class ExchangeFacade(
        @Named(GdaxApi.NAME)
        private val gdaxApi: ExchangeApi,

        @Named(CoinbaseApi.NAME)
        private val coinbaseApi: ExchangeApi,

        @Named(CryptonatorApi.NAME)
        private val cryptonatorApi: ExchangeApi,

        private val preferences: UserPreferences
) {

    fun getRate(currencyCode: String): RateResponse {
        return getApi().getRate(currencyCode, preferences.fiatCurrency())
    }

    private fun getApi(): ExchangeApi {
        val exchangeApiCode = preferences.exchangeApi()
        return when (exchangeApiCode) {
            "GdaxApi" -> gdaxApi
            "CoinbaseApi" -> coinbaseApi
            "CryptonatorApi" -> cryptonatorApi
            else -> throw IllegalArgumentException("No such api $exchangeApiCode")
        }
    }

}