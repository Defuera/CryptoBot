package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.blockchain.BitcoinInfoApi
import javax.inject.Singleton

@Module
class BlockchainModule {

    @Provides
    @Singleton
    fun provideBitcoinInfoApi(okHttpClient: OkHttpClient): BitcoinInfoApi = BitcoinInfoApi(okHttpClient)

}