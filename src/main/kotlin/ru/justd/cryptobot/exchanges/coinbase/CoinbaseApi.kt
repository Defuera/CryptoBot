package ru.justd.cryptobot.exchanges.coinbase

import com.google.gson.Gson
import khronos.Dates
import khronos.toString
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.RequestFailedException

private const val BASE_URL = "https://api.coinbase.com/v2"
private const val HEADER_CB_VERSION = "CB-VERSION"
private const val FORMAT_API_DATE = "yyyy-MM-dd"

/**
 * https://developers.coinbase.com/api/
 */
class CoinbaseApi(private val okHttpClient: OkHttpClient) : ExchangeApi {

    companion object { const val NAME = "CoinbaseApi" }

    val gson = Gson()

    //todo add parse data test
    override fun getRate(cryptoCurrencyCode: String, fiatCurrency: String): RateResponse {

        val response = okHttpClient.newCall(
                Request.Builder()
                        .get()
                        .url("$BASE_URL/prices/$cryptoCurrencyCode-$fiatCurrency/spot")
                        .header(HEADER_CB_VERSION, Dates.today.toString(FORMAT_API_DATE))
                        .build()
        ).execute()

        val bodyString = response.body()?.string()
        if (bodyString != null) {
            val envelope = gson.fromJson<RateResponseEnvelope>(bodyString, RateResponseEnvelope::class.java)
            if (envelope.errors?.isNotEmpty() == true) {
                throw RequestFailedException(envelope.errors.first().message)
            } else {
                return envelope.data
            }
        } else {
            throw RuntimeException("oioi") //todo
        }
    }

    private data class RateResponseEnvelope(val data: RateResponse, val errors: Array<Error>?)

}