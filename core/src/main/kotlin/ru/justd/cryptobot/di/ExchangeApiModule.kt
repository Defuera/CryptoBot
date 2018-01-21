package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacadeImpl
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.purchase.PurchaseFacade
import ru.justd.cryptobot.handler.purchase.PurchaseFacadeImpl
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
    fun provideExchangeFacade(okHttpClient: OkHttpClient): ExchangeApiFacade = ExchangeApiFacadeImpl(okHttpClient)

    @Provides
    @Singleton
    fun providePurchaseFacade(okHttpClient: OkHttpClient): PurchaseFacade = PurchaseFacadeImpl(GdaxApi(okHttpClient))

}