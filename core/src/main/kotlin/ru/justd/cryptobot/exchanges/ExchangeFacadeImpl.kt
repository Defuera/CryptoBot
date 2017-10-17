package ru.justd.cryptobot.exchanges

import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.bitfinex.BitfinexApi
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import javax.inject.Named

class ExchangeFacadeImpl(
        @Named(GdaxApi.NAME)
        private val gdaxApi: ExchangeApi,

        @Named(CoinbaseApi.NAME)
        private val coinbaseApi: ExchangeApi,

        @Named(CryptonatorApi.NAME)
        private val cryptonatorApi: ExchangeApi,

        @Named(BitfinexApi.NAME)
        private val bitfinexApi: ExchangeApi,

        private val preferences: UserPreferences
) : ExchangeFacade {

    @Throws(ExchangeNotSupported::class)
    override fun getRate(base: String?, target: String?, exchangeApiCode: String?): RateResponse {
        return getApi(exchangeApiCode ?: preferences.exchangeApi())
                .getRate(
                        base ?: preferences.baseCurrency(),
                        target ?: preferences.targetCurrency()
                )
    }

    @Throws(ExchangeNotSupported::class)
    private fun getApi(exchangeApiCode: String): ExchangeApi {
        return when (exchangeApiCode.toUpperCase()) {
            GdaxApi.NAME -> gdaxApi
            CoinbaseApi.NAME -> coinbaseApi
            CryptonatorApi.NAME -> cryptonatorApi
            BitfinexApi.NAME -> bitfinexApi
            else -> throw ExchangeNotSupported(exchangeApiCode)
        }
    }

}