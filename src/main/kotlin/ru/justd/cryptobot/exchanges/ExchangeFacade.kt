package ru.justd.cryptobot.exchanges

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.gdax.GdaxApi

class ExchangeFacade {

    //todo inject
    //region dependencies

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    private val api = GdaxApi(okHttpClient)
//    private val api = CoinbaseApi(okHttpClient)
    //    private val api = CryptonatorApi(okHttpClient)
    private val preferences = UserPreferences()

    //endregion


    fun getRate(currencyCode: String): RateResponse {
        return api.getRate(currencyCode, preferences.fiatCurrency())
    }

}