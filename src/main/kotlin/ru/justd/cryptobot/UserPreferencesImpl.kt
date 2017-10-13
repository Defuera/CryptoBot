package ru.justd.cryptobot

import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import java.util.*

class UserPreferencesImpl : UserPreferences {

    override fun baseCurrency(): String = "BTC"

    override fun targetCurrency(): String = "USD"

    override fun exchangeApi(): String = CoinbaseApi.NAME

    override fun locale(): Locale = Locale.getDefault()
}