package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.blockchain.BitcoinInfoApi
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import javax.inject.Named
import javax.inject.Singleton


const val NAMED_BLOCKCHAIN_API_BITCOIN = "BitcoinBlockchainInfoAPi"

@Module
class BlockchainModule {

    @Provides
    @Singleton
    @Named(NAMED_BLOCKCHAIN_API_BITCOIN)
    fun provideBitcoinInfoApi(okHttpClient: OkHttpClient): BlockchainApi = BitcoinInfoApi(okHttpClient)

}