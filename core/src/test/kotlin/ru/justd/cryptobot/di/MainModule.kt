package ru.justd.cryptobot.di

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.publisher.Publisher
import utils.DateManager
import javax.inject.Singleton

@Module(includes = arrayOf(ExchangeApiModule::class, BlockchainModule::class, StorageModule::class))
class MainModule {

    companion object {
        val dateManagerMock: DateManager = mock()
    }

    @Provides
    @Singleton
    fun provideCommandHandlerFacade(
            exchangeFacade: ExchangeApiFacade,
            blockchainApi: BlockchainApi,
            storage: Storage,
            dateManager: DateManager
    ): CommandHandlerFacade = mock()

    @Provides
    @Singleton
    fun providePublisher(exchangeFacade: ExchangeApiFacade, storage: Storage, dateManager: DateManager): Publisher = mock()

    @Provides
    @Singleton
    fun provideDateManager(): DateManager = dateManagerMock

}