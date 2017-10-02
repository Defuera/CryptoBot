package ru.justd.cryptobot.exchanges.coinbase

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.RateResponse
import java.text.SimpleDateFormat
import java.util.*

/**
 * https://developers.coinbase.com/api/
 */
class CoinbaseApi(private val okHttpClient: OkHttpClient) : ExchangeApi {

    val BASE_URL = "https://api.coinbase.com/v2"
    val HEADER_CB_VERSION = "CB-VERSION"
    val gson = Gson()

    //todo add parse data test
    override fun getRate(cryptoCurrencyCode: String, fiatCurrency: String): RateResponse {

        // Format for input
        val dateParser = SimpleDateFormat("yyyy-MM-dd")
        val date: String = dateParser.format(Date())

        val response = okHttpClient.newCall(
                Request.Builder()
                        .get()
                        .url("$BASE_URL/prices/$cryptoCurrencyCode-$fiatCurrency/spot")
                        .header(HEADER_CB_VERSION, date)
                        .build()
        ).execute()

        val bodyString = response.body()?.string()
        if (bodyString != null) {
            val rateResponse = gson.fromJson<RateResponseEnvelope>(bodyString, RateResponseEnvelope::class.java).data
            return rateResponse
        } else {
            throw RuntimeException("oioi") //todo
        }
    }

    private data class RateResponseEnvelope(val data: RateResponse)

}