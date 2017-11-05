package ru.justd.cryptobot.api.exchanges.bitfinex

import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.exchanges.PollingExchange
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed

private const val BASE_URL = "https://api.bitfinex.com/v1"

/**
 * https://docs.bitfinex.com/v1/reference
 */
class BitfinexApi(okHttpClient: OkHttpClient) : PollingExchange(okHttpClient) {

    companion object {
        const val NAME = "BITFINEX"
    }

    override fun getRateUrl(base: String, target: String) = "$BASE_URL/pubticker/$base$target"

    @Throws(RequestFailed::class)
    override fun parseResponseBody(bodyString: String, base: String, target: String): RateResponse {
        val ticker = gson.fromJson<Ticker>(bodyString, Ticker::class.java)
        return RateResponse(ticker.mid, base, target)
    }

    private data class Ticker(
            val mid: Double,
            val bid: Double,
            val ask: Double,
            val last_price: Double,
            val low: Double,
            val high: Double,
            val volume: Double,
            val timestamp: Double
    )

}