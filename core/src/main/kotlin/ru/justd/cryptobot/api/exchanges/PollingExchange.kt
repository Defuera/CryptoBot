package ru.justd.cryptobot.api.exchanges

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed

abstract class PollingExchange(private val okHttpClient: OkHttpClient) : ExchangeApi {

    val gson = Gson()

    /**
     * https://docs.gdax.com/#get-product-order-book
     */
    @Throws(RequestFailed::class)
    override fun getRate(base: String, target: String): RateResponse {
        val response = okHttpClient
                .newCall(requestBuilder(base, target).build())
                .execute()

        val bodyString = response.body()?.string()
        if (bodyString != null && !bodyString.isNullOrBlank()) {
            return parseRateResponse(bodyString, base, target)
        } else {
            throw RuntimeException("Unexpected error occured")
        }
    }

    open fun requestBuilder(base: String, target: String): Request.Builder {
        return Request.Builder()
                .get()
                .url(getRateUrl(base, target))
    }

    abstract fun getRateUrl(base: String, target: String): String

    @Throws(RequestFailed::class)
    abstract fun parseRateResponse(bodyString: String, base: String, target: String): RateResponse

}