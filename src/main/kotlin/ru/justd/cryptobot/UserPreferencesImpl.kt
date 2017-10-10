package ru.justd.cryptobot

import java.util.*

class UserPreferencesImpl : UserPreferences {

    override fun baseCurrency(): String = "BTC"

    override fun targetCurrency(): String = "USD"

    override fun exchangeApi(): String = "CoinbaseApi"

    override fun locale(): Locale = Locale.getDefault()
}