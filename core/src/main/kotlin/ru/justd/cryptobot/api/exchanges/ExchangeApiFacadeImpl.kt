package ru.justd.cryptobot.api.exchanges

import ru.justd.cryptobot.api.exchanges.bitfinex.BitfinexApi
import ru.justd.cryptobot.api.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.api.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import javax.inject.Named

class ExchangeApiFacadeImpl(
        @Named(GdaxApi.NAME)
        private val gdaxApi: ExchangeApi,

        @Named(CoinbaseApi.NAME)
        private val coinbaseApi: ExchangeApi,

        @Named(CryptonatorApi.NAME)
        private val cryptonatorApi: ExchangeApi,

        @Named(BitfinexApi.NAME)
        private val bitfinexApi: ExchangeApi

) : ExchangeApiFacade {

    //todo make ExchangeApiFacade implement ExchangeApi and remove ExchangeApiFacade?

    @Throws(ExchangeNotSupported::class, RequestFailed::class)
    override fun getRate(base: String, target: String, exchangeApiCode: String): RateResponse {
        return getApi(exchangeApiCode).getRate(base, target)
    }

    override fun getCryptoAssets(exchange: String): Array<String> {
        return getApi(exchange).getCryptoAssets()
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