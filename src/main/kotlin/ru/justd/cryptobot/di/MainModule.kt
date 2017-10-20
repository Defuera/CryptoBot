package ru.justd.cryptobot.di

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.TelegramBotAdapter
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.BuildConfig
import ru.justd.cryptobot.Publisher
import ru.justd.cryptobot.PublisherImpl
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
import ru.justd.cryptobot.persistance.Storage
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class, StorageModule::class))
class MainModule {

    @Provides
    @Singleton
    fun provideTelegramBotAdapter(): TelegramBot = TelegramBotAdapter.build(BuildConfig.BOT_TOKEN) //todo provide debug/production bot based on BuildType

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeFacade,
            storage: Storage
    ): CommandHandlerFacade = CommandHandlerFacadeImpl(exchangeFacade, storage)

    @Provides
    @Singleton
    fun providePublisher(
            telegramBot: TelegramBot, //todo Messenger should be here instead
            exchangeFacade: ExchangeFacade,
            storage: Storage
    ): Publisher = PublisherImpl(telegramBot, exchangeFacade, storage)

}