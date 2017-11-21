package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
import ru.justd.cryptobot.handler.InstantFactory
import ru.justd.cryptobot.handler.about.AboutCommandHandler
import ru.justd.cryptobot.handler.help.HelpCommandHandler
import ru.justd.cryptobot.handler.price.PriceCommandHandlerFactory
import ru.justd.cryptobot.handler.subscribe.SubscribeFactory
import ru.justd.cryptobot.handler.unsubscribe.UnsubscribeHandlerFactory
import ru.justd.cryptobot.handler.update.UpdateCommandHandler
import ru.justd.cryptobot.handler.wallet.WalletInfoHandlerFactory
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.PublisherImpl
import utils.TimeManager
import utils.UuidGenerator
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class, BlockchainModule::class, StorageModule::class, UtilsModule::class))
class MainModule {

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeApiFacade,
            blockchainApi: BlockchainApi,
            storage: Storage,
            timeManager: TimeManager,
            uuidGenerator: UuidGenerator
    ): CommandHandlerFacade = CommandHandlerFacadeImpl(
            mutableListOf(
                    InstantFactory("/about", AboutCommandHandler),
                    InstantFactory("/help", HelpCommandHandler),
                    InstantFactory("/update", UpdateCommandHandler),
                    PriceCommandHandlerFactory(exchangeFacade),
                    SubscribeFactory(exchangeFacade, storage, timeManager, uuidGenerator),
                    UnsubscribeHandlerFactory(storage),
                    WalletInfoHandlerFactory(blockchainApi)
            )
    )

    @Provides
    @Singleton
    fun providePublisher(
            exchangeFacade: ExchangeApiFacade,
            storage: Storage,
            timeManager: TimeManager
    ): Publisher = PublisherImpl(exchangeFacade, storage, timeManager)

}