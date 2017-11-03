package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import org.mockito.Mockito.mock
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import javax.inject.Named
import javax.inject.Singleton

@Module
class BlockchainModule {

    companion object {

        val bitcoinInfoApiMock = mock(BlockchainApi::class.java)
    }

    @Provides
    @Singleton
    @Named(NAMED_BLOCKCHAIN_API_BITCOIN)
    fun provideBitcoinInfoApi(okHttpClient: OkHttpClient): BlockchainApi = bitcoinInfoApiMock

}