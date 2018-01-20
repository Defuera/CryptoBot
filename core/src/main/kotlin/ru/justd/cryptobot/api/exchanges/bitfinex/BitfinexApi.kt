package ru.justd.cryptobot.api.exchanges.bitfinex

import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.exchanges.PollingExchange
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed

private const val BASE_URL = "https://api.bitfinex.com/v1"

/**
 * https://docs.bitfinex.com/v1/reference
 */
class BitfinexApi(val okHttpClient: OkHttpClient) : PollingExchange(okHttpClient) {

    companion object {
        const val NAME = "BITFINEX"
    }

    @Throws(RequestFailed::class)
    override fun getRate(base: String, target: String): RateResponse {
        val responseJson = executeRequest("$BASE_URL/pubticker/$base$target")

        val ticker = gson.fromJson<Ticker>(responseJson, Ticker::class.java)
        val errorMessage = ticker.message
        if (errorMessage.isNullOrBlank()) {
            return RateResponse(ticker.mid, base, target)
        } else {
            //then error
            throw RequestFailed("Error occurred: $errorMessage")
        }
    }

    override fun getCryptoAssets(): Array<String> {
        val responseJson = executeRequest("$BASE_URL/symbols")
        val typeToken = object : TypeToken<List<String>>() {}.type
        val symbols = gson.fromJson<List<String>>(responseJson, typeToken)
        return symbols.map { it.substring(0..2).toUpperCase() }
                .toSet()
                .toTypedArray()

    }

    private data class Ticker(
            val mid: Double,
            val bid: Double,
            val ask: Double,
            val last_price: Double,
            val low: Double,
            val high: Double,
            val volume: Double,
            val timestamp: Double,
            val message: String
    )

}