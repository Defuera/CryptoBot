package ru.justd.cryptobot.api.blockchain.ether

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.justd.cryptobot.api.blockchain.AddressInfo
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.api.blockchain.PATH_ADDRESS

const val BASE_URL = "https://api.blockcypher.com/v1/eth/main"
const val ENDPOINT_URL = "/addrs/$PATH_ADDRESS/balance"

class EtherInfoApi(private val okHttpClient: OkHttpClient) : BlockchainApi {

    private val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    override fun getAddressInfo(address: String): AddressInfo {
        val response = okHttpClient
                .newCall(requestBuilder(address).build())
                .execute()

        val bodyString = response.body()?.string()
        if (!bodyString.isNullOrBlank()) {
            return gson.fromJson(bodyString, EtherAddressInfo::class.java)
        } else {
            throw RuntimeException("oioi") //todo unexpected error together with failed request (newCall.execute())?
        }
    }

    private fun requestBuilder(address: String): Request.Builder {
        return Request.Builder()
                .get()
                .url("$BASE_URL${ENDPOINT_URL.replace(PATH_ADDRESS, address)}")
    }


}