package ru.justd.cryptobot.exchanges.cryptonator

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.exceptions.RequestFailed
import ru.justd.cryptotrader.api.cryptonator.model.TickerEnvelope


private const val BASE_URL = "https://api.cryptonator.com/api/"

/**
 * https://www.cryptonator.com/api
 */
class CryptonatorApi(val okHttpClient: OkHttpClient) : ExchangeApi {

    companion object { const val NAME = "CRYPTONATOR" }

    val gson = Gson() //todo move to abstract parent class together with okHttpClient? But I think gson should not be injected, since it may be different configuration for every api impl.

    override fun getRate(base: String, target: String): RateResponse {//todo

        val response = okHttpClient.newCall(
                Request.Builder()
                        .get()
                        .url("$BASE_URL/ticker/$base-$target")
                        .build()
        ).execute()

        val bodyString = response.body()?.string()
        if (bodyString != null) {
            val envelope = gson.fromJson<TickerEnvelope>(bodyString, TickerEnvelope::class.java)
            if (envelope.success) {
                val ticker = envelope.ticker!!
                return RateResponse(ticker.price, ticker.base, ticker.target)
            } else {
                throw RequestFailed(envelope.error)
            }
        } else {
            throw RuntimeException("oioi") //todo also to base class?
        }
    }

}