package ru.justd.cryptobot.api.exchanges

import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.exchanges.bitfinex.BitfinexApi
import ru.justd.cryptobot.api.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi

class ExchangeApiFacadeImpl(val okHttpClient: OkHttpClient) : ExchangeApiFacade {

    @Throws(ExchangeNotSupported::class, RequestFailed::class)
    override fun getRate(base: String, target: String, exchangeApiCode: String): RateResponse {
        return getApi(exchangeApiCode).getRate(base, target)
    }

    override fun getCryptoAssets(exchange: String): Array<String> {
        return getApi(exchange).getCryptoAssets()
    }

    override fun listExchanges(): Array<String>
            = Exchange.values()
            .map { it.displayName }
            .toTypedArray()

    @Throws(ExchangeNotSupported::class)
    private fun getApi(exchangeApiCode: String): ExchangeApi {
        return when (exchangeApiCode.toUpperCase()) {
            Exchange.GDAX.name -> GdaxApi(okHttpClient)
            Exchange.COINBASE.name -> CoinbaseApi(okHttpClient)
            Exchange.BITFINEX.name -> BitfinexApi(okHttpClient)
            else -> throw ExchangeNotSupported(exchangeApiCode)
        }
    }

}

enum class Exchange(val displayName: String) {
    GDAX("Gdax"),
    COINBASE("Coinbase"),
    BITFINEX("Bitfinex")
}
