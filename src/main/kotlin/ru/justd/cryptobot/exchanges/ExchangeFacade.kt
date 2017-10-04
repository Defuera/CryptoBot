package ru.justd.cryptobot.exchanges

import ru.justd.cryptobot.UserPreferences
import javax.inject.Named

open class ExchangeFacade( //todo open for tests, which sucks
        @Named("GdaxApi")
        private val gdaxApi: ExchangeApi,

        @Named("CoinbaseApi")
        private val coinbaseApi: ExchangeApi,

        @Named("CryptonatorApi")
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