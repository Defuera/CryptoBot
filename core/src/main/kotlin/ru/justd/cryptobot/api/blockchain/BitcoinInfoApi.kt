package ru.justd.cryptobot.api.blockchain

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class BitcoinInfoApi(private val okHttpClient: OkHttpClient) : BlockchainApi {

    private val BASE_URL = "https://bitaps.com/api"
    private val ENDPOINT_ADDRESS = "address"
    private val gson = Gson()

    /**
     * https://docs.gdax.com/#get-product-order-book
     */
    override fun getAddressInfo(address: String): AddressInfo {
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

