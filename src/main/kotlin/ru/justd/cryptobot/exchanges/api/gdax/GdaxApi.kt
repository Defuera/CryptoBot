package ru.justd.cryptobot.exchanges.api.gdax

import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.RateResponse

/**
 * https://docs.gdax.com/
 */
class GdaxApi(private val okHttpClient: OkHttpClient) : ExchangeApi { //todo not finished

    private val BASE_URL = "https://api.gdax.com"

//    private val GET_PRODUCT_TICKER  = "/products/{}/ticker"

    /**
     * GET /products/<product-id>/ticker
     */
    override fun getRate(currencyCode: String, fiatCurrency: String): RateResponse {
        val response = okHttpClient.newCall(
                Request.Builder()
                        .url(BASE_URL + "/products/{}/ticker")
                        .build()
        ).execute()

        val responseString = response.body().toString()
        return RateResponse(.0,"","")
    }

}