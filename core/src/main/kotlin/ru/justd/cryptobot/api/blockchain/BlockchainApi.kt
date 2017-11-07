package ru.justd.cryptobot.api.blockchain


const val PATH_ADDRESS = "{address}"

interface BlockchainApi {

    fun getAddressInfo(address: String): AddressInfo
}