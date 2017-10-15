package ru.justd.cryptobot.exchanges.coinbase

import khronos.Dates
import khronos.toString
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.exchanges.PollingExchange
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.exceptions.RequestFailed

private const val BASE_URL = "https://api.coinbase.com/v2"
private const val HEADER_CB_VERSION = "CB-VERSION"
private const val FORMAT_API_DATE = "yyyy-MM-dd"

/**
 * https://developers.coinbase.com/api/
 */
class CoinbaseApi(okHttpClient: OkHttpClient) : PollingExchange(okHttpClient) {

    companion object { const val NAME = "COINBASE" }

    override fun getRateUrl(base: String, target: String) = "$BASE_URL/prices/$base-$target/spot"

    override fun requestBuilder(base: String, target: String): Request.Builder {
        return super
                .requestBuilder(base, target)
                .header(HEADER_CB_VERSION, Dates.today.toString(FORMAT_API_DATE))
    }

    @Throws(RequestFailed::class)
    override fun parseResponseBody(bodyString: String, base: String, target: String): RateResponse {
        val envelope = gson.fromJson<RateResponseEnvelope>(bodyString, RateResponseEnvelope::class.java)
        if (envelope.errors?.isNotEmpty() == true) {
            throw RequestFailed(envelope.errors.first().message)
        } else {
            return envelope.data
        }
    }

    private data class RateResponseEnvelope(val data: RateResponse, val errors: Array<Error>?)

}