package ru.justd.cryptobot.api.blockchain

import ru.justd.cryptobot.api.blockchain.bitcoin.BitcoinAddressValidator
import ru.justd.cryptobot.handler.exceptions.InvalidCommand

class BlockchainInfoApiFacade(
        private val bitcoinInfoApi: BlockchainApi,
        private val etherInfoApi: BlockchainApi
) : BlockchainApi {

    override fun getAddressInfo(address: String): AddressInfo {
        val delegate: BlockchainApi = findDelegate(address)
        return delegate.getAddressInfo(address)
    }

    private fun findDelegate(address: String): BlockchainApi {
        if (BitcoinAddressValidator.validateBitcoinAddress(address)) {
            return bitcoinInfoApi
        } else if (true) { //todo validator
            return etherInfoApi
        } else {
            throw InvalidCommand("cannot recognize address")
        }
    }
}