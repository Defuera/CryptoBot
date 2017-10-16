package ru.justd.cryptobot

import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.handler.subscribe.StorageException
import java.util.*

class UserPreferencesImpl : UserPreferences {
    override fun baseCurrency(): String = "BTC"

    override fun targetCurrency(): String = "USD"

    override fun exchangeApi(): String = CoinbaseApi.NAME

    override fun locale(): Locale = Locale.getDefault()

    @Throws(StorageException::class)
    override fun storeSubscription(base: String?, target: String?, exchange: String?) {
        throw StorageException("storage not implemented")
    }

}
