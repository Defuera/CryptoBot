package ru.justd.cryptobot.api.blockchain.ether

object EtherAddressValidator {

    fun validateAddress(address: String) = Regex("^(0x)?[0-9a-f]{40}\$").matches(address.toLowerCase())
}