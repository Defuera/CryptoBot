package ru.justd.cryptobot

import java.util.*

interface UserPreferences {

    fun baseCurrency(): String

    fun targetCurrency(): String

    fun exchangeApi(): String

    fun locale(): Locale

}