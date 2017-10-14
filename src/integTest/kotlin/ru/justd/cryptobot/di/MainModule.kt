package ru.justd.cryptobot.di

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.BuildConfig
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.ExchangeApi
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.ExchangeFacadeImpl
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class, StorageModule::class))
class MainModule(instancE_ID: String) {

    @Provides
    @Singleton
    fun provideTelegramBotAdapter(): TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeFacade
    ): CommandHandlerFacade = CommandHandlerFacadeImpl(exchangeFacade, userPreferences)


    @Provides
    @Singleton
    fun provideExchangeFacade(
            @Named(GdaxApi.NAME) gdaxApi: ExchangeApi,
            @Named(CoinbaseApi.NAME) coinbaseApi: ExchangeApi,
            @Named(CryptonatorApi.NAME) cryptonatorApi: ExchangeApi,
            userPreferences: UserPreferences
    ): ExchangeFacade = ExchangeFacadeImpl(gdaxApi, coinbaseApi, cryptonatorApi, userPreferences)


}