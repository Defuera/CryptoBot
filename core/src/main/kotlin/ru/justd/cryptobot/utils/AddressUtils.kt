package ru.justd.cryptobot.utils

import ru.justd.cryptobot.api.blockchain.bitcoin.BitcoinAddressValidator
import ru.justd.cryptobot.api.blockchain.ether.EtherAddressValidator
import ru.justd.cryptobot.api.blockchain.litecoin.LitecoinAddressValidator

object AddressUtils {

    fun validateAddress(address: String, base: String): Boolean {
        return when (base.toUpperCase()) {
            "BTC" -> BitcoinAddressValidator.validateAddress(address)
            "ETH" -> EtherAddressValidator.validateAddress(address)
            "LTC" -> LitecoinAddressValidator.validateAddress(address)
            else -> throw IllegalArgumentException("cannot validate $base")
        }
    }

}