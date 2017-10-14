package ru.justd.cryptobot.di

import com.pengrad.telegrambot.TelegramBot
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mockito.Mockito
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.CommandHandlerFacade
import javax.inject.Named
import javax.inject.Singleton

@Module
class ExchangeApiModule {

    companion object {

        val telegramBot = Mockito.mock(TelegramBot::class.java)

        val commandHandlerFacade = Mockito.mock(CommandHandlerFacade::class.java)

        val exchangeFacade = Mockito.mock(ExchangeFacade::class.java)

    }

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
    fun provideExchangeFacade(
            @Named(GdaxApi.NAME) gdaxApi: ExchangeApi,
            @Named(CoinbaseApi.NAME) coinbaseApi: ExchangeApi,
            @Named(CryptonatorApi.NAME) cryptonatorApi: ExchangeApi,
            userPreferences: UserPreferences
    ) =  exchangeFacade

}