package ru.justd.cryptobot.exchanges

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

abstract class PollingExchange(private val okHttpClient: OkHttpClient) : ExchangeApi {

    val gson = Gson()

    /**
     * https://docs.gdax.com/#get-product-order-book
     */
    override fun getRate(base: String, target: String): RateResponse {

        val response = okHttpClient.newCall(
                Request.Builder()
                        .get()
                        .url(getRateUrl(base, target))
                        .build()
        ).execute()

        val bodyString = response.body()?.string()
        if (!bodyString.isNullOrBlank()) {
            return parseResponseBody(bodyString!!, base, target) //todo why !! ?
        } else {
            throw RuntimeException("oioi") //todo also to base class?
        }
    }

    abstract fun getRateUrl(base: String, target: String): String

    abstract fun parseResponseBody(bodyString: String, base: String, target: String): RateResponse

}