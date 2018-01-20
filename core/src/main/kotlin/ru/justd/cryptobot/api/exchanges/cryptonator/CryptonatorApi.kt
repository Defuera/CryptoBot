package ru.justd.cryptobot.api.exchanges.cryptonator

import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.exchanges.PollingExchange
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed
import ru.justd.cryptotrader.api.cryptonator.model.TickerEnvelope


private const val BASE_URL = "https://api.cryptonator.com/api/"

/**
 * https://www.cryptonator.com/api
 */
class CryptonatorApi(okHttpClient: OkHttpClient) : PollingExchange(okHttpClient) {

    companion object { const val NAME = "CRYPTONATOR" }

    @Throws(RequestFailed::class)
    override fun getRate(base: String, target: String): RateResponse {
        val responseJson = executeRequest("$BASE_URL/ticker/$base-$target")

        val envelope = gson.fromJson<TickerEnvelope>(responseJson, TickerEnvelope::class.java)
        if (envelope.success) {
            val ticker = envelope.ticker!!
            return RateResponse(ticker.price, ticker.base, ticker.target)
        } else {
            throw RequestFailed(envelope.error)
        }
    }

    override fun getCryptoAssets(): Array<String> {
        return arrayOf("BTC", "ETH")
    }

}