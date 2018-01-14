package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.api.exchanges.ExchangeApi
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacadeImpl
import ru.justd.cryptobot.api.exchanges.bitfinex.BitfinexApi
import ru.justd.cryptobot.api.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.api.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.purchase.PurchaseFacade
import ru.justd.cryptobot.handler.purchase.PurchaseFacadeImpl
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
    @Named(GdaxApi.NAME)
    fun provideGdaxApi(okHttpClient: OkHttpClient): ExchangeApi = GdaxApi(okHttpClient)

    @Provides
    @Singleton
    @Named(CoinbaseApi.NAME)
    fun provideCoinbaseApi(okHttpClient: OkHttpClient): ExchangeApi = CoinbaseApi(okHttpClient)

    @Provides
    @Singleton
    @Named(CryptonatorApi.NAME)
    fun provideCryptonator(okHttpClient: OkHttpClient): ExchangeApi = CryptonatorApi(okHttpClient)

    @Provides
    @Singleton
    @Named(BitfinexApi.NAME)
    fun provideBitfinexApi(okHttpClient: OkHttpClient): ExchangeApi = BitfinexApi(okHttpClient)

    @Provides
    @Singleton
    fun provideExchangeFacade(
            @Named(GdaxApi.NAME) gdaxApi: ExchangeApi,
            @Named(CoinbaseApi.NAME) coinbaseApi: ExchangeApi,
            @Named(CryptonatorApi.NAME) cryptonatorApi: ExchangeApi,
            @Named(BitfinexApi.NAME) bitfinexApi: ExchangeApi
    ): ExchangeApiFacade = ExchangeApiFacadeImpl(
            gdaxApi,
            coinbaseApi,
            cryptonatorApi,
            bitfinexApi
    )

    @Provides
    @Singleton
    fun providePurchaseFacade(@Named(GdaxApi.NAME) gdaxApi: ExchangeApi) : PurchaseFacade = PurchaseFacadeImpl(gdaxApi as PurchaseApi) //todo explicit cast
}