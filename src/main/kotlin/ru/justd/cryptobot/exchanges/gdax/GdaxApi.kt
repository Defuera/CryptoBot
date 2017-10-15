package ru.justd.cryptobot.exchanges.gdax

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import ru.justd.cryptobot.exchanges.PollingExchange
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.exceptions.RequestFailed

/**
 * https://docs.gdax.com/
 */
class GdaxApi(okHttpClient: OkHttpClient) : PollingExchange(okHttpClient) {

    companion object { const val NAME = "GDAX" }

    private val BASE_URL = "https://api.gdax.com"

    /**
     * https://docs.gdax.com/#get-product-order-book
     */

    override fun getRateUrl(base: String, target: String) = "$BASE_URL/products/$base-$target/book" //todo extract to exchangeApi

    override fun parseResponseBody(bodyString: String, base: String, target: String): RateResponse {
        val envelope = gson.fromJson<Envelope>(bodyString, Envelope::class.java)
        if (envelope.bids != null) {
            return RateResponse(envelope.bids[0][0].toDouble(), base, target)
        } else {
            throw RequestFailed(envelope.errorMessage!!)
        }
    }

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