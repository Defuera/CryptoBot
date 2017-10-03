package ru.justd.cryptobot.exchanges.gdax

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.RequestFailedException

/**
 * https://docs.gdax.com/
 */
class GdaxApi(private val okHttpClient: OkHttpClient) : ExchangeApi {

    private val BASE_URL = "https://api.gdax.com"
    private val gson = Gson()

    /**
     * https://docs.gdax.com/#get-product-order-book
     */
    override fun getRate(cryptoCurrencyCode: String, fiatCurrency: String): RateResponse {

        val response = okHttpClient.newCall(
                Request.Builder()
                        .get()
                        .url(getRateUrl(cryptoCurrencyCode, fiatCurrency))
                        .build()
        ).execute()

        val bodyString = response.body()?.string()
        if (bodyString != null) {
            val envelope = gson.fromJson<Envelope>(bodyString, Envelope::class.java)
            if (envelope.bids != null) {
                return RateResponse(envelope.bids[0][0].toDouble(), cryptoCurrencyCode, fiatCurrency)
            } else {
                throw RequestFailedException(envelope.errorMessage!!)
            }
        } else {
            throw RuntimeException("oioi") //todo also to base class?
        }
    }

    private fun getRateUrl(cryptoCurrencyCode: String, fiatCurrency: String) = "$BASE_URL/products/$cryptoCurrencyCode-$fiatCurrency/book" //todo extract to exchangeApi

    private data class Envelope(

            @SerializedName("message")
            val errorMessage: String?,
            val sequence: Long?,
            /**
             *  [ price, size, num-orders ]
             */
            val bids: Array<Array<String>>?,
            /**
             * [ price, size, num-orders ]
             */
            val asks: Array<Array<String>>?
    )
}