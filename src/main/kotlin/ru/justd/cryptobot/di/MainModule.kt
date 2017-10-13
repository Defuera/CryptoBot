package ru.justd.cryptobot.di

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.BuildConfig
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.UserPreferencesImpl
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class))
class MainModule {

    @Provides
    @Singleton
    fun provideUserPreferences(): UserPreferences = UserPreferencesImpl()

    @Provides
    @Singleton
    fun provideTelegramBotAdapter(): TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeFacade
    ): CommandHandlerFacade = CommandHandlerFacadeImpl(exchangeFacade)

}