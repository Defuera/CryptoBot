package ru.justd.cryptobot.di

import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.api.blockchain.BlockchainInfoApiFacade
import javax.inject.Named
import javax.inject.Singleton

@Module
class BlockchainModule {

    companion object {

        val blockchainInfoApiFacade = mock<BlockchainInfoApiFacade>()
    }

    @Provides
    @Singleton
    @Named(NAMED_BLOCKCHAIN_API_BITCOIN)
    fun provideBitcoinInfoApi(okHttpClient: OkHttpClient): BlockchainApi = mock()

    @Provides
    @Singleton
    @Named(NAMED_BLOCKCHAIN_API_ETHER)
    fun provideEtherInfoApi(okHttpClient: OkHttpClient): BlockchainApi = mock()

    @Provides
    @Singleton
    fun provideBlockchainInfoApiFacade(
            @Named(NAMED_BLOCKCHAIN_API_BITCOIN) bitcoinInfoApi: BlockchainApi,
            @Named(NAMED_BLOCKCHAIN_API_ETHER) etherInfoApi: BlockchainApi
    ): BlockchainInfoApiFacade = mock()

}