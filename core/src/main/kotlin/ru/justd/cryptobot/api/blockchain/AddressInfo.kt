package ru.justd.cryptobot.api.blockchain

import ru.justd.cryptobot.CryptoCurrency

interface AddressInfo {

    fun getBalance(): Double
    fun getSymbol(): CryptoCurrency

}
