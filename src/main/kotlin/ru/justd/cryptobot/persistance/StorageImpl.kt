package ru.justd.cryptobot.persistance

import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.StorageException
import java.util.*
import kotlin.collections.HashMap

class StorageImpl : Storage {

    val storageStub = HashMap<String, UserPreferences>()

    private val DEFAULT_BASE = "BTC"

    private val DEFAULT_TARGET = "USD"

    private val DEFAULT_EXCHAGE = GdaxApi.NAME

    private val DEFAULT_LOCALE = Locale.getDefault() //todo


    override fun getBaseCurrency(id: String) = storageStub[id]?.base ?: DEFAULT_BASE

    override fun setBaseCurrency(id: String, base: String) {//todo
    }

    override fun getTargetCurrency(id: String) = storageStub[id]?.target ?: DEFAULT_TARGET

    override fun setTargetCurrency(id: String, base: String) {//todo
    }

    override fun getExchangeApi(id: String) = storageStub[id]?.exchangeCode ?: DEFAULT_EXCHAGE

    override fun setExchangeApi(id: String, exchangeApiName: String) {//todo
    }

    override fun getLocale(id: String) = storageStub[id]?.locale ?: DEFAULT_LOCALE

    override fun setLocale(id: String, locale: Locale) {//todo
    }

    override fun getSubscription(id: String) = storageStub[id]?.subscription

    override fun getSubscriptions(): List<Subscription> {
        return storageStub.map { it.value.subscription }
    }

    @Throws(StorageException::class)
    override fun setSubscription(id: String, newSubscription: Subscription) {
        val key = "id"
        storageStub.put(
                key,
                storageStub[key]
                        ?.copy(subscription = newSubscription)
                        ?:
                        UserPreferences(
                                getBaseCurrency(id),
                                getTargetCurrency(id),
                                getExchangeApi(id),
                                getLocale(id),
                                newSubscription
                        )
        )
    }


}
