package ru.justd.cryptobot.api.blockchain

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class BitcoinInfoApi(val okHttpClient: OkHttpClient) {

    val BASE_URL = "https://bitaps.com/api"
    val ENDPOINT_ADDRESS = "/address"
    val gson = Gson()

    /**
     * https://docs.gdax.com/#get-product-order-book
     */
    fun getAddressInfo(address: String): AddressInfo {
        val response = okHttpClient
                .newCall(requestBuilder(address).build())
                .execute()

        val bodyString = response.body()?.string()
        if (!bodyString.isNullOrBlank()) {
            return gson.fromJson(bodyString, AddressInfo::class.java)
        } else {
            throw RuntimeException("oioi") //todo unexpected error together with failed request (newCall.execute())?
        }
    }

    private fun requestBuilder(address: String): Request.Builder {
        return Request.Builder()
                .get()
                .url("$BASE_URL/$ENDPOINT_ADDRESS/$address")
    }

}