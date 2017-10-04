package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.ExchangeFacade
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class))
class MainModule {

    @Provides
    @Singleton
    fun provideUserPreferences(): UserPreferences = UserPreferences()

    @Provides
    @Singleton
    fun provideExchangeFacade(
            @Named("GdaxApi") gdaxApi: ExchangeApi,
            @Named("CoinbaseApi") coinbaseApi: ExchangeApi,
            @Named("CryptonatorApi") cryptonatorApi: ExchangeApi,
            userPreferences: UserPreferences
    ): ExchangeFacade = ExchangeFacade(gdaxApi, coinbaseApi, cryptonatorApi, userPreferences)
}