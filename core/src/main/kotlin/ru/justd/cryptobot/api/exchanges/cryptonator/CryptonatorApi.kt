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

    override fun getRateUrl(base: String, target: String) = "$BASE_URL/ticker/$base-$target"

    @Throws(RequestFailed::class)
    override fun parseResponseBody(bodyString: String, base: String, target: String): RateResponse {
        val envelope = gson.fromJson<TickerEnvelope>(bodyString, TickerEnvelope::class.java)
        if (envelope.success) {
            val ticker = envelope.ticker!!
            return RateResponse(ticker.price, ticker.base, ticker.target)
        } else {
            throw RequestFailed(envelope.error)
        }
    }

}