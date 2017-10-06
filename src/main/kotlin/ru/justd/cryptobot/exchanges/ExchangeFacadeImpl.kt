package ru.justd.cryptobot.exchanges

import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import javax.inject.Named

class ExchangeFacadeImpl(
        @Named(GdaxApi.NAME)
        private val gdaxApi: ExchangeApi,

        @Named(CoinbaseApi.NAME)
        private val coinbaseApi: ExchangeApi,

        @Named(CryptonatorApi.NAME)
        private val cryptonatorApi: ExchangeApi,

        private val preferences: UserPreferences
) : ExchangeFacade {

    override fun getRate(currencyCode: String): RateResponse {
        return getApi().getRate(currencyCode, preferences.fiatCurrency())
    }

    private fun getApi(): ExchangeApi {
        val exchangeApiCode = preferences.exchangeApi()
        return when (exchangeApiCode) {
            GdaxApi.NAME -> gdaxApi
            CoinbaseApi.NAME -> coinbaseApi
            CryptonatorApi.NAME -> cryptonatorApi
            else -> throw IllegalArgumentException("No such api $exchangeApiCode")
        }
    }

}