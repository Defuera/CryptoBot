package ru.justd.cryptobot.api.exchanges.coinbase

import khronos.Dates
import khronos.toString
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.api.exchanges.PollingExchangeApi
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed

private const val BASE_URL = "https://api.coinbase.com/v2"
private const val HEADER_CB_VERSION = "CB-VERSION"
private const val FORMAT_API_DATE = "yyyy-MM-dd"

/**
 * https://developers.coinbase.com/api/
 */
class CoinbaseApi(okHttpClient: OkHttpClient) : PollingExchangeApi(okHttpClient) {

    @Throws(RequestFailed::class)
    override fun getRate(cryptoAsset: String, fiatCurrency: String): RateResponse {
        val responseJson = executeRequest("$BASE_URL/prices/$cryptoAsset-$fiatCurrency/buy")

        val envelope = gson.fromJson<RateResponseEnvelope>(responseJson, RateResponseEnvelope::class.java)
        if (envelope.errors?.isNotEmpty() == true) {
            throw RequestFailed(envelope.errors.first().message)
        } else {
            return envelope.data
        }
    }

    //could not find api method to fetch list of available crypto assets
    override fun getCryptoAssets() = arrayOf("BTC", "LTC", "ETH")

    override fun createRequestBuilder(url: String): Request.Builder {
        return super
                .createRequestBuilder(url)
                .header(HEADER_CB_VERSION, Dates.today.toString(FORMAT_API_DATE))
    }

    private data class RateResponseEnvelope(val data: RateResponse, val errors: Array<Error>?)
}