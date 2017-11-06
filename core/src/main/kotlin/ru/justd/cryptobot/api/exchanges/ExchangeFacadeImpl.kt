package ru.justd.cryptobot.api.exchanges

import ru.justd.cryptobot.api.exchanges.bitfinex.BitfinexApi
import ru.justd.cryptobot.api.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.api.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.persistance.Storage
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

        private val storage: Storage
) : ExchangeFacade {

    @Throws(ExchangeNotSupported::class)
    override fun getRate(base: String?, target: String?, exchangeApiCode: String?): RateResponse {
        return getApi(exchangeApiCode ?: storage.getExchangeApi("chatId")) //todo pass userId
                .getRate(
                        base ?: storage.getBaseCurrency("chatId"),
                        target ?: storage.getTargetCurrency("chatId")
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