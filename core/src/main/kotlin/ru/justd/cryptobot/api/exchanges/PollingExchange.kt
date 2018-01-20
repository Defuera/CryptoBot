package ru.justd.cryptobot.api.exchanges

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request

abstract class PollingExchange(private val okHttpClient: OkHttpClient) : ExchangeApi {

    val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    fun executeRequest(url: String): String {
        val response = okHttpClient
                .newCall(createRequestBuilder(url).build())
                .execute()

        val bodyString = response.body()?.string()
        if (bodyString != null && !bodyString.isNullOrBlank()) {
            return bodyString
        } else {
            throw RuntimeException("Unexpected error occured")
        }
    }

    open fun createRequestBuilder(url: String): Request.Builder {
        return Request.Builder()
                .get()
                .url(url)
    }

}