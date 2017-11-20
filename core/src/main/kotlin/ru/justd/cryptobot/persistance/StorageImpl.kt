package ru.justd.cryptobot.persistance

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import ru.justd.cryptobot.handler.subscribe.Subscription
import java.util.*
import kotlin.collections.ArrayList

class StorageImpl constructor(private val storageDataSource: HashMap<String, UserPreferences>) : Storage {

    private val updateSubject = BehaviorSubject.create<PreferenceUpdate>()

    @Throws(StorageException::class) //todo try to execute publisher before adding the subscription, to check weather it's valid
    override fun addSubscription(channelId: String, newSubscription: Subscription) { //todo check if already exists, if so - modify
        val userPreferences = storageDataSource[channelId]
        val newPreference = addSubscriptionToExistingPreference(userPreferences, newSubscription)
                ?: createPreference(newSubscription)

        storageDataSource.put(channelId, newPreference)
        updateSubject.onNext(PreferenceUpdate(channelId, newPreference))
    }

    override fun removeSubscription(channelId: String, subscriptionId: String) {
        val userPreferences = storageDataSource[channelId] ?: throw IllegalArgumentException("subscription do not exist")
        val oldSubscriptions = userPreferences.subscriptions ?: throw IllegalArgumentException("subscription do not exist")

        storageDataSource.put(
                channelId,
                userPreferences.copy(subscriptions = oldSubscriptions.filter { it.uuid != subscriptionId })
        )
    }

    override fun getSubscriptions(channelId: String): List<Subscription>? = storageDataSource[channelId]?.subscriptions

    override fun getSubscriptions(): List<Subscription>? {
        val subscriptionsLists = storageDataSource
                .values
                .map { it.subscriptions }
                .filter { it != null && it.isNotEmpty() }


        return when(subscriptionsLists.size){
            0 -> null
            else -> subscriptionsLists.reduce { left, right -> left!! + right!! }
        }
    }

    override fun observeSubscriptionUpdates(): Observable<PreferenceUpdate> = this.updateSubject

    //region private helper methods

    private fun addSubscriptionToExistingPreference(userPreferences: UserPreferences?, newSubscription: Subscription): UserPreferences? {
        if (userPreferences == null) {
            return null
        }

        val existingSubscriptions = userPreferences.subscriptions

        val newSubscriptionsList = (existingSubscriptions ?: ArrayList()) + newSubscription

        return userPreferences.copy(subscriptions = newSubscriptionsList)
    }

    private fun createPreference(newSubscription: Subscription): UserPreferences {
        return UserPreferences(listOf(newSubscription))
    }

    //endregion

}
