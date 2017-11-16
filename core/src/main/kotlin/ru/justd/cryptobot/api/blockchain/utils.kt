package ru.justd.cryptobot.api.blockchain


const val SATOSHI_IN_BITCOIN = 100_000_000
const val ETH_DIVIDER = 1000_000_000

fun satoshiToBitcoin(balance: String): Double {
    return balance.toDouble() / SATOSHI_IN_BITCOIN
}

fun ethToDecimal(balance: String): Double {
    return balance.toDouble() / ETH_DIVIDER
}