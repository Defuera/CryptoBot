package ru.justd.cryptobot.di

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.purchase.PurchaseFacade
import javax.inject.Singleton

@Module
class ExchangeApiModule {

    companion object {

        val exchangeFacade = mock<ExchangeApiFacade>()

    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = mock<OkHttpClient>()

    @Provides
    @Singleton
    fun provideExchangeFacade(okHttpClient: OkHttpClient) = exchangeFacade

    @Provides
    @Singleton
    fun providePurchaseFacade(okHttpClient: OkHttpClient) = mock<PurchaseFacade>()
}