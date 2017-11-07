package ru.justd.cryptobot.api.blockchain.bitcoin

import ru.justd.cryptobot.CryptoCurrency
import ru.justd.cryptobot.api.blockchain.AddressInfo
import ru.justd.cryptobot.api.blockchain.satoshiToBitcoin

data class BitcoinAddressInfo(
        val balance: String,
        val confirmed_balance: String,
        val received: String,
        val sent: String,
        val pending: String,
        val multisigReceived: String,
        val multisigSent: String,
        val txReceived: String,
        val txSent: String,
        val txMultisig_received: String,
        val txMultisig_sent: String,
        val txUnconfirmed: String,
        val txInvalid: String
) : AddressInfo {

    override fun getBalance() = satoshiToBitcoin(balance)
    override fun getSymbol() = CryptoCurrency.BTC

}