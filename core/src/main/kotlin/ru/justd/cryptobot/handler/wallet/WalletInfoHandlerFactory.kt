package ru.justd.cryptobot.handler.wallet

import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.di.NAMED_BLOCKCHAIN_API_BITCOIN
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import javax.inject.Named

class WalletInfoHandlerFactory(
        @Named(NAMED_BLOCKCHAIN_API_BITCOIN) private val bitcoinInfoApi: BlockchainApi
) : CommandHandlerFactory<WalletInfoHandler>("/info") {

    override fun create(channelId: String, request: String): WalletInfoHandler {
        val address = retrieveArg(request, 0) ?: throw InvalidCommand("please provide bitcoin ledger address")
        return WalletInfoHandler(bitcoinInfoApi, address)
    }

}