package ru.justd.cryptobot

import java.util.*

class UserPreferences {

    fun fiatCurrency(): String = "USD"

    fun locale(): Locale = Locale.getDefault()

    fun exchangeApi(): String = "CoinbaseApi"
}