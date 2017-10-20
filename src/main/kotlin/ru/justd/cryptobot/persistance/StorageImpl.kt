package ru.justd.cryptobot.persistance

import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.StorageException
import java.util.*

class StorageImpl constructor(val storageDataSource : HashMap<String, UserPreferences>): Storage {

    private val DEFAULT_BASE = "BTC"
    private val DEFAULT_TARGET = "USD"
    private val DEFAULT_EXCHAGE = GdaxApi.NAME
    private val DEFAULT_LOCALE = Locale.getDefault() //todo

    val observers = MutableList<((UserPreferences) -> Unit)?>(0, { null }) //todo rxify

    override fun getBaseCurrency(id: String) = storageDataSource[id]?.base ?: DEFAULT_BASE

    override fun setBaseCurrency(id: String, base: String) {//todo
    }

    override fun getTargetCurrency(id: String) = storageDataSource[id]?.target ?: DEFAULT_TARGET

    override fun setTargetCurrency(id: String, base: String) {//todo
    }

    override fun getExchangeApi(id: String) = storageDataSource[id]?.exchangeCode ?: DEFAULT_EXCHAGE

    override fun setExchangeApi(id: String, exchangeApiName: String) {//todo
    }

    override fun getLocale(id: String) = storageDataSource[id]?.locale ?: DEFAULT_LOCALE

    override fun setLocale(id: String, locale: Locale) {//todo
    }

    override fun getSubscriptions(id: String): List<Subscription>? = storageDataSource[id]?.subscriptions

    override fun getSubscriptionsByChatId(): Map<String, List<Subscription>> {
        return storageDataSource
                .filterValues { it.subscriptions.isNotEmpty() }
                .mapValues { it.value.subscriptions }
    }

    @Throws(StorageException::class)
    override fun addSubscription(userId: String, newSubscription: Subscription) {
        val userPreferences = storageDataSource[userId]
        val newPreference = addSubscriptionToExistingPreference(userPreferences, newSubscription)
                ?: createPreference(userId, newSubscription)

        storageDataSource.put(userId, newPreference)

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
