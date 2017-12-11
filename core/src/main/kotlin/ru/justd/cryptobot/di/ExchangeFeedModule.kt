package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.exchanges.ExchangeFeed
import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.api.exchanges.gdax.GdaxFeed
import javax.inject.Singleton

@Module
class ExchangeFeedModule {

    @Provides
    @Singleton
    fun providePriceFeed(gdaxFeed: ExchangeFeed) = ExchangeFeedFacade(gdaxFeed)

    @Provides
    @Singleton
    fun provideExchangeFeed(okHttpClient: OkHttpClient): ExchangeFeed = GdaxFeed(okHttpClient)

}