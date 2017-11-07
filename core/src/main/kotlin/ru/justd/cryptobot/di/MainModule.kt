package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.api.blockchain.BlockchainInfoApiFacade
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
import ru.justd.cryptobot.handler.InstantFactory
import ru.justd.cryptobot.handler.about.AboutCommandHandler
import ru.justd.cryptobot.handler.help.HelpCommandHandler
import ru.justd.cryptobot.handler.price.PriceCommandHandlerFactory
import ru.justd.cryptobot.handler.subscribe.SubscribeFactory
import ru.justd.cryptobot.handler.update.UpdateCommandHandler
import ru.justd.cryptobot.handler.wallet.WalletInfoHandlerFactory
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.PublisherImpl
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class, BlockchainModule::class, StorageModule::class))
class MainModule {

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeApiFacade,
            blockchainInfoApiFacade: BlockchainInfoApiFacade,
            storage: Storage
    ): CommandHandlerFacade = CommandHandlerFacadeImpl(
            mutableListOf(
                    InstantFactory("/about", AboutCommandHandler),
                    InstantFactory("/help", HelpCommandHandler),
                    InstantFactory("/update", UpdateCommandHandler),
                    PriceCommandHandlerFactory(exchangeFacade),
                    SubscribeFactory(storage),
                    WalletInfoHandlerFactory(blockchainInfoApiFacade)
            )
    )

    @Provides
    @Singleton
    fun providePublisher(
            exchangeFacade: ExchangeApiFacade,
            storage: Storage
    ): Publisher = PublisherImpl(exchangeFacade, storage)

}