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
        val responseJson = executeRequest(getRateUrl(base, target))
        return parseRateResponseBody(responseJson, base, target)
    }

    fun executeRequest(url: String): String {
        val response = okHttpClient
                .newCall(createRequestBuilder(url).build())
                .execute()

        val bodyString = response.body()?.string()
        if (bodyString != null && !bodyString.isNullOrBlank()) {
            return bodyString
        } else {
            throw RuntimeException("Unexpected error occured")
        }
    }


    //region abstract

    abstract fun getRateUrl(base: String, target: String): String

    @Throws(RequestFailed::class)
    abstract fun parseRateResponseBody(bodyString: String, base: String, target: String): RateResponse

    open fun createRequestBuilder(url : String): Request.Builder {
        return Request.Builder()
                .get()
                .url(url)
    }

    //endregion

}