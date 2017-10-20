package ru.justd.cryptobot.persistance

import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.StorageException
import java.util.*
import kotlin.collections.HashMap

class StorageImpl : Storage {

    val storageStub = HashMap<String, UserPreferences>()

    val observers = MutableList<((UserPreferences) -> Unit)?>(0, { null }) //todo rxify

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

    override fun getSubscriptions(id: String) = storageStub[id]?.subscriptions

    override fun getSubscriptionsByChatId(): Map<String, List<Subscription>> {
        return storageStub
                .filterValues { it.subscriptions.isNotEmpty() }
                .mapValues { it.value.subscriptions }
    }

    @Throws(StorageException::class)
    override fun addSubscription(id: String, newSubscription: Subscription) {
        val key = "id"
        val userPreferences = storageStub[key]
        val newPreference = addSubscriptionToExistingPreference(userPreferences, newSubscription)
                ?: createPreference(id, newSubscription)

        storageStub.put(key, newPreference)

        observers.forEach { it?.invoke(newPreference) }
    }

    private fun addSubscriptionToExistingPreference(userPreferences: UserPreferences?, newSubscription: Subscription): UserPreferences? {
        if (userPreferences == null) {
            return null
        }

        val existingSubscriptions = userPreferences.subscriptions

        val newSubscriptionsList = existingSubscriptions + newSubscription

        return userPreferences.copy(subscriptions = newSubscriptionsList)

    }

    private fun createPreference(id: String, newSubscription: Subscription): UserPreferences {
        return UserPreferences(
                getBaseCurrency(id),
                getTargetCurrency(id),
                getExchangeApi(id),
                getLocale(id),
                listOf(newSubscription)
        )
    }

    override fun subscribeToUpdates(observer: (UserPreferences) -> Unit) {
        observers.add(observer)
    }

}
