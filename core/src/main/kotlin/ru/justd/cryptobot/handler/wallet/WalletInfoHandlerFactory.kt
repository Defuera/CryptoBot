package ru.justd.cryptobot.handler.wallet

import ru.justd.cryptobot.api.blockchain.BlockchainInfoApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand

class WalletInfoHandlerFactory(
        private val facade: BlockchainInfoApiFacade
) : CommandHandlerFactory<WalletInfoHandler>("/info") {

    override fun create(channelId: String, request: String): WalletInfoHandler {
        val address = retrieveArg(request, 0) ?: throw InvalidCommand("please provide bitcoin ledger address")
        return WalletInfoHandler(facade, address)
    }

}