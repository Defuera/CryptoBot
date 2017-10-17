package ru.justd.cryptobot

import ru.justd.cryptobot.handler.subscribe.StorageException
import java.util.*

interface UserPreferences {

    fun baseCurrency(): String

    fun targetCurrency(): String

    fun exchangeApi(): String

    fun locale(): Locale

    @Throws(StorageException::class)
    fun storeSubscription(base: String?, target: String?, exchange: String?)

}