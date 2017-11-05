package ru.justd.cryptobot.handler.wallet

import ru.justd.cryptobot.api.blockchain.AddressInfo
import ru.justd.cryptobot.api.blockchain.BlockchainApi
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply


const val SATOSHI_IN_BITCOIN = 100_000_000

class WalletInfoHandler(
        private val bitcoinInfoApi: BlockchainApi,
        private val address: String
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        val addressInfo = bitcoinInfoApi.getAddressInfo(address)
        val amountBitcoins = satoshiToBitcoin(addressInfo)
        return Reply(channelId, "You have $amountBitcoins BTC")
    }

    private fun satoshiToBitcoin(addressInfo: AddressInfo) : Double {
        return addressInfo.balance.toDouble() / SATOSHI_IN_BITCOIN
    }

}