package ru.justd.cryptobot.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.justd.cryptobot.api.blockchain.bitcoin.BitcoinInfoApi
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.api.blockchain.BlockchainInfoApiFacade
import ru.justd.cryptobot.api.blockchain.ether.EtherInfoApi
import javax.inject.Named
import javax.inject.Singleton


const val NAMED_BLOCKCHAIN_API_BITCOIN = "BitcoinBlockchainInfoAPi"
const val NAMED_BLOCKCHAIN_API_ETHER = "EtherBlockchainInfoAPi"

@Module
class BlockchainModule {

    @Provides
    @Singleton
    @Named(NAMED_BLOCKCHAIN_API_BITCOIN)
    fun provideBitcoinInfoApi(okHttpClient: OkHttpClient): BlockchainApi = BitcoinInfoApi(okHttpClient)

    @Provides
    @Singleton
    @Named(NAMED_BLOCKCHAIN_API_ETHER)
    fun provideEtherInfoApi(okHttpClient: OkHttpClient): BlockchainApi = EtherInfoApi(okHttpClient)

    @Provides
    @Singleton
    fun provideBlockchainInfoApiFacade(
            @Named(NAMED_BLOCKCHAIN_API_BITCOIN) bitcoinInfoApi : BlockchainApi,
            @Named(NAMED_BLOCKCHAIN_API_ETHER) etherInfoApi : BlockchainApi
    ): BlockchainApi = BlockchainInfoApiFacade(
            bitcoinInfoApi,
            etherInfoApi
    )

}