package ru.justd.cryptobot.api.blockchain.litecoin

object LitecoinAddressValidator {
    fun validateAddress(address: String) = Regex("/^L[a-km-zA-HJ-NP-Z1-9]{26,33}\$/").matches(address)
}