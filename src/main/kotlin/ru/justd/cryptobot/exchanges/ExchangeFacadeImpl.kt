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

    override fun getRate(base: String) = getRate(base, preferences.targetCurrency())

    override fun getRate(base: String, target: String) = getRate(base, preferences.targetCurrency(), preferences.exchangeApi())

    override fun getRate(base: String, target: String, exchangeApiCode: String): RateResponse {
        val api = getApi(exchangeApiCode)
        return api.getRate(base, target)
    }

    private fun getApi(exchangeApiCode: String): ExchangeApi {
        return when (exchangeApiCode) {
            GdaxApi.NAME -> gdaxApi
            CoinbaseApi.NAME -> coinbaseApi
            CryptonatorApi.NAME -> cryptonatorApi
            else -> throw IllegalArgumentException("No such api $exchangeApiCode")
        }
    }

}