package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
import ru.justd.cryptobot.handler.InstantFactory
import ru.justd.cryptobot.handler.about.AboutCommandHandler
import ru.justd.cryptobot.handler.help.HelpCommandHandler
import ru.justd.cryptobot.handler.price.PriceCommandHandlerFactory
import ru.justd.cryptobot.handler.subscribe.SubscribeFactory
import ru.justd.cryptobot.handler.update.UpdateCommandHandler
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.PublisherImpl
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class, StorageModule::class))
class MainModule {

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeFacade,
            storage: Storage
    ): CommandHandlerFacade = CommandHandlerFacadeImpl(
            mutableListOf(
                    InstantFactory("/about", AboutCommandHandler),
                    InstantFactory("/help", HelpCommandHandler),
                    InstantFactory("/update", UpdateCommandHandler),
                    PriceCommandHandlerFactory(exchangeFacade),
                    SubscribeFactory(storage)
            )
    )

    @Provides
    @Singleton
    fun providePublisher(
            exchangeFacade: ExchangeFacade,
            storage: Storage
    ): Publisher = PublisherImpl(exchangeFacade, storage)

}