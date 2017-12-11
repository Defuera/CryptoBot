package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.api.exchanges.ExchangeApi
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFacadeImpl
import ru.justd.cryptobot.handler.feedback.FeedbackHandlerFactory
import ru.justd.cryptobot.handler.price.PriceCommandHandlerFactory
import ru.justd.cryptobot.handler.subscribe.SubscribeFactory
import ru.justd.cryptobot.handler.trader.TradeHandlerFactory
import ru.justd.cryptobot.handler.unsubscribe.UnsubscribeHandlerFactory
import ru.justd.cryptobot.handler.wallet.AddressInfoHandlerFactory
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.PublisherImpl
import utils.TimeManager
import utils.UuidGenerator
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class, ExchangeFeedModule::class, BlockchainModule::class, StorageModule::class, UtilsModule::class, AnalyticsModule::class))
class MainModule(val debug: Boolean) {

    @Provides
    @Singleton
    @Named("IsDebug")
    fun provideIsDebug() = debug

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeApiFacade,
            blockchainApi: BlockchainApi,
            storage: Storage,
            timeManager: TimeManager,
            uuidGenerator: UuidGenerator,
            feedbackStorage: FeedbackStorage,
            analytics: Analytics,
            exchangeFeedFacade: ExchangeFeedFacade,
            @Named(GdaxApi.NAME) gdaxApi: ExchangeApi
    ): CommandHandlerFacade = CommandHandlerFacadeImpl(
            mutableListOf(
                    PriceCommandHandlerFactory(analytics, exchangeFacade),
                    SubscribeFactory(analytics, exchangeFacade, storage, timeManager, uuidGenerator),
                    UnsubscribeHandlerFactory(analytics, storage),
                    AddressInfoHandlerFactory(analytics, blockchainApi),
                    FeedbackHandlerFactory(analytics, feedbackStorage),
                    TradeHandlerFactory(exchangeFeedFacade, gdaxApi as GdaxApi) //todo bad boy
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