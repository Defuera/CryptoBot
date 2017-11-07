package ru.justd.cryptobot.handler.wallet

import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply

class WalletInfoHandler(
        private val facade: BlockchainApi,
        private val address: String
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        val addressInfo = facade.getAddressInfo(address)
        return Reply(channelId, "You have ${addressInfo.getBalance()} ${addressInfo.getSymbol()}")
    }

}