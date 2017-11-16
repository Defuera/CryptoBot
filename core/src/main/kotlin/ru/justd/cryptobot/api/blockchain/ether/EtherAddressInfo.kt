package ru.justd.cryptobot.api.blockchain.ether

import ru.justd.cryptobot.CryptoCurrency
import ru.justd.cryptobot.api.blockchain.AddressInfo
import ru.justd.cryptobot.api.blockchain.ethToDecimal

data class EtherAddressInfo(
        val address: String,
        val totalReceived: String,
        val totalSent: String,
        val balance: String,
        val unconfirmedBalance: String,
        val finalBalance: String,
        val nTx: String,
        val unconfirmedNTx: String,
        val finalNTx: String
) : AddressInfo {

    override fun getBalance() = ethToDecimal(balance)
    override fun getSymbol() = CryptoCurrency.ETH

}