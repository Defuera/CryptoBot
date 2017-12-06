package ru.justd.cryptobot.handler.wallet

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand

class AddressInfoHandlerFactory(
        private val analytics: Analytics,
        private val facade: BlockchainApi
) : CommandHandlerFactory<AddressInfoHandler>("/addressinfo") {

    override fun create(channelId: String, request: String): AddressInfoHandler {
        val address = retrieveArg(request, 0) ?: throw InvalidCommand("Provide bitcoin or ether ledger address")
        return AddressInfoHandler(analytics, facade, address)
    }

}