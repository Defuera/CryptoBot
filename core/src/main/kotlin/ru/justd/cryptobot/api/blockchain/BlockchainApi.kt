package ru.justd.cryptobot.api.blockchain

interface BlockchainApi {

    fun getAddressInfo(address: String): AddressInfo
}