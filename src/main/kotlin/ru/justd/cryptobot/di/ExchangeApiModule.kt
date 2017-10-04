package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import javax.inject.Named
import javax.inject.Singleton

@Module
class ExchangeApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()

    @Provides
    @Singleton
    @Named("GdaxApi")
    fun provideGdaxApi(okHttpClient: OkHttpClient): ExchangeApi = GdaxApi(okHttpClient)

    @Provides
    @Singleton
    @Named("GdaxApi")
    fun provideCoinbaseApi(okHttpClient: OkHttpClient): ExchangeApi = CoinbaseApi(okHttpClient)

    @Provides
    @Singleton
    @Named("GdaxApi")
    fun provideCryptonator(okHttpClient: OkHttpClient): ExchangeApi = CryptonatorApi(okHttpClient)


}