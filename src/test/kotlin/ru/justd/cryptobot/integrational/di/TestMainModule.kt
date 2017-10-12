package ru.justd.cryptobot.di

import com.pengrad.telegrambot.TelegramBot
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.CommandHandlerFacade
import javax.inject.Singleton

@Module
class TestMainModule {

    @Provides
    @Singleton
    fun provideUserPreferences() = mock(UserPreferences::class.java)

    @Provides
    @Singleton
    fun provideExchangeFacade() = mock(ExchangeFacade::class.java)

    @Provides
    @Singleton
    fun provideTelegramBotAdapter() = mock(TelegramBot::class.java)

    @Provides
    @Singleton
    fun provideCommandHandlerFacade() = mock(CommandHandlerFacade::class.java)

}