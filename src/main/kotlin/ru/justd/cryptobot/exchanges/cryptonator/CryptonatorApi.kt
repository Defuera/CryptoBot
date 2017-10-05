package ru.justd.cryptobot.exchanges.cryptonator

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.RequestFailedException
import ru.justd.cryptotrader.api.cryptonator.model.TickerEnvelope


private const val BASE_URL = "https://api.cryptonator.com/api/"

/**
 * https://www.cryptonator.com/api
 */
class CryptonatorApi(val okHttpClient: OkHttpClient) : ExchangeApi {

    companion object { const val NAME = "CryptonatorApi" }

    val gson = Gson() //todo move to abstract parent class together with okHttpClient? But I think gson should not be injected, since it may be different configuration for every api impl.

    override fun getRate(cryptoCurrencyCode: String, fiatCurrency: String): RateResponse {//todo

        val response = okHttpClient.newCall(
                Request.Builder()
                        .get()
                        .url("$BASE_URL/ticker/$cryptoCurrencyCode-$fiatCurrency")
                        .build()
        ).execute()

        val bodyString = response.body()?.string()
        if (bodyString != null) {
            val envelope = gson.fromJson<TickerEnvelope>(bodyString, TickerEnvelope::class.java)
            if (envelope.success) {
                val ticker = envelope.ticker!!
                return RateResponse(ticker.price, ticker.base, ticker.target)
            } else {
                throw RequestFailedException(envelope.error)
            }
        } else {
            throw RuntimeException("oioi") //todo also to base class?
        }
    }

}