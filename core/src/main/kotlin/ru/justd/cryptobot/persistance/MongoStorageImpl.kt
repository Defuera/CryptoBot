package ru.justd.cryptobot.persistance

import com.google.gson.Gson
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.UpdateOptions
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import org.bson.Document
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.Subscription
import java.util.*
import kotlin.collections.ArrayList

class MongoStorageImpl(private val mongo: MongoDatabase) : Storage {

    private val updateSubject = BehaviorSubject.create<PreferenceUpdate>()

    override fun getSubscriptions(channelId: String): List<Subscription>? =
            getPreferences(channelId)?.subscriptions

    override fun getSubscriptions(): List<Subscription>? {
        //todo
        return null
    }

    override fun addSubscription(channelId: String, newSubscription: Subscription) {
        updateSubject.onNext(PreferenceUpdate(
                channelId,
                updateProperty(
                        channelId,
                        { it.copy(subscriptions = (it.subscriptions ?: ArrayList()) + newSubscription) }
                )
        ))
    }

    override fun removeSubscription(channelId: String, subscriptionId: String) {
        //todo
    }

    override fun observeSubscriptionUpdates(): Observable<PreferenceUpdate> = updateSubject

    private fun getPreferences(channelId: String): UserPreferences? =
            getPreferencesCollection()
                    .find(eq(PROPERTY_ID, channelId))
                    .firstOrNull()
                    ?.toJson()
                    ?.let { Gson().fromJson(it, UserPreferences::class.java) }

    private fun getPreferencesCollection(): MongoCollection<Document> = mongo.getCollection(COLLECTION_NAME)

    private fun updateProperty(channelId: String, preferencesUpdate: (UserPreferences) -> UserPreferences): UserPreferences {
        val preferences = getPreferences(channelId) ?: UserPreferences()
        val updatedPreferences = preferencesUpdate.invoke(preferences)
        val update = Document().append(
                "\$set",
                Document.parse(Gson().toJson(updatedPreferences))
        )

        getPreferencesCollection()
                .updateOne(
                        eq(PROPERTY_ID, channelId),
                        update,
                        UpdateOptions().upsert(true)
                )

        return updatedPreferences
    }

    companion object {
        private const val COLLECTION_NAME = "preferences"

        private const val PROPERTY_ID = "_id"

        private const val DEFAULT_BASE_CURRENCY = "BTC"
        private const val DEFAULT_TARGET_CURRENCY = "USD"
        private const val DEFAULT_EXCHANGE = GdaxApi.NAME
        private val DEFAULT_LOCALE = Locale.getDefault()
    }

}
