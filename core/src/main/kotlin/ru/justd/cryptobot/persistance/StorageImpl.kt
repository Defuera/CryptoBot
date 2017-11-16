package ru.justd.cryptobot.persistance

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.StorageException
import ru.justd.cryptobot.handler.subscribe.Subscription
import java.util.*
import kotlin.collections.ArrayList

class StorageImpl constructor(val storageDataSource: HashMap<String, UserPreferences>) : Storage {

    private val DEFAULT_BASE = "BTC"

    private val DEFAULT_TARGET = "USD"
    private val DEFAULT_EXCHAGE = GdaxApi.NAME
    private val DEFAULT_LOCALE = Locale.getDefault() //todo
    private val updateSubject = BehaviorSubject.create<PreferenceUpdate>()

    override fun getBaseCurrency(channelId: String) = storageDataSource[channelId]?.base ?: DEFAULT_BASE

    override fun setBaseCurrency(channelId: String, base: String) {//todo
    }

    override fun getTargetCurrency(channelId: String) = storageDataSource[channelId]?.target ?: DEFAULT_TARGET

    override fun setTargetCurrency(channelId: String, base: String) {//todo
    }

    override fun getExchangeApi(channelId: String) = storageDataSource[channelId]?.exchangeCode ?: DEFAULT_EXCHAGE

    override fun setExchangeApi(channelId: String, exchangeApiName: String) {//todo
    }

    override fun getLocale(channelId: String) = storageDataSource[channelId]?.locale ?: DEFAULT_LOCALE

    override fun setLocale(channelId: String, locale: Locale) {//todo
    }

    override fun getSubscriptions(channelId: String): List<Subscription>? = storageDataSource[channelId]?.subscriptions

    @Throws(StorageException::class) //todo try to execute publisher before adding the subscription, to check weather it's valid
    override fun addSubscription(channelId: String, newSubscription: Subscription) { //todo check if already exists, if so - modify
        val userPreferences = storageDataSource[channelId]
        val newPreference = addSubscriptionToExistingPreference(userPreferences, newSubscription)
                ?: createPreference(channelId, newSubscription)

        storageDataSource.put(channelId, newPreference)
        updateSubject.onNext(PreferenceUpdate(channelId, newPreference))
    }

    private fun addSubscriptionToExistingPreference(userPreferences: UserPreferences?, newSubscription: Subscription): UserPreferences? {
        if (userPreferences == null) {
            return null
        }

        val existingSubscriptions = userPreferences.subscriptions

        val newSubscriptionsList = (existingSubscriptions ?: ArrayList()) + newSubscription

        return userPreferences.copy(subscriptions = newSubscriptionsList)

    }

    private fun createPreference(channelId: String, newSubscription: Subscription): UserPreferences {
        return UserPreferences(
                getBaseCurrency(channelId),
                getTargetCurrency(channelId),
                getExchangeApi(channelId),
                getLocale(channelId),
                listOf(newSubscription)
        )
    }

    override fun observeUpdates(): Observable<PreferenceUpdate> = this.updateSubject

}
