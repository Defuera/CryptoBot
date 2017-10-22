package ru.justd.cryptobot.di

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.mockito.Mockito
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.bitfinex.BitfinexApi
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.persistance.Storage
import javax.inject.Named
import javax.inject.Singleton

@Module
class ExchangeApiModule {

    companion object {

        val exchangeFacade = Mockito.mock(ExchangeFacade::class.java)

    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = mock<OkHttpClient>()

    @Provides
    @Singleton
    @Named(GdaxApi.NAME)
    fun provideGdaxApi(okHttpClient: OkHttpClient) = mock<ExchangeApi>()

    @Provides
    @Singleton
    @Named(CoinbaseApi.NAME)
    fun provideCoinbaseApi(okHttpClient: OkHttpClient) = mock<ExchangeApi>()

    @Provides
    @Singleton
    @Named(CryptonatorApi.NAME)
    fun provideCryptonator(okHttpClient: OkHttpClient) = mock<ExchangeApi>()

    @Provides
    @Singleton
    @Named(BitfinexApi.NAME)
    fun provideBitfinexApi(okHttpClient: OkHttpClient) = mock<ExchangeApi>()

    @Provides
    @Singleton
    fun provideExchangeFacade(
            @Named(GdaxApi.NAME) gdaxApi: ExchangeApi,
            @Named(CoinbaseApi.NAME) coinbaseApi: ExchangeApi,
            @Named(CryptonatorApi.NAME) cryptonatorApi: ExchangeApi,
            @Named(BitfinexApi.NAME) bitfinexApi: ExchangeApi,
            storage: Storage
    ) = exchangeFacade

}