//package ru.justd.cryptobot.di
//
//import com.nhaarman.mockito_kotlin.mock
//import dagger.Module
//import dagger.Provides
//import ru.justd.cryptobot.api.blockchain.BlockchainApi
//import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
//import ru.justd.cryptobot.handler.CommandHandlerFacade
//import ru.justd.cryptobot.persistance.Storage
//import ru.justd.cryptobot.publisher.Publisher
//import utils.TimeManager
//import javax.inject.Singleton
//
//@Module(includes = arrayOf(ExchangeApiModule::class, BlockchainModule::class, StorageModule::class))
//class MainModule {
//
//    companion object {
//        val TIME_MANAGER_MOCK: TimeManager = mock()
//    }
//
//    @Provides
//    @Singleton
//    fun provideCommandHandlerFacade(
//            exchangeFacade: ExchangeApiFacade,
//            blockchainApi: BlockchainApi,
//            storage: Storage,
//            timeManager: TimeManager
//    ): CommandHandlerFacade = mock()
//
//    @Provides
//    @Singleton
//    fun providePublisher(exchangeFacade: ExchangeApiFacade, storage: Storage, timeManager: TimeManager): Publisher = mock()
//
////    @Provides
////    @Singleton
////    fun provideDateManager(): TimeManager = TIME_MANAGER_MOCK
//
//}