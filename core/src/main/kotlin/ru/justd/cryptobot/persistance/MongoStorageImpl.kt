package ru.justd.cryptobot.persistance

import com.google.gson.Gson
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.ne
import com.mongodb.client.model.UpdateOptions
import io.reactivex.subjects.BehaviorSubject
import org.bson.Document
import ru.justd.cryptobot.handler.subscribe.Subscription

class MongoStorageImpl(private val mongo: MongoDatabase) : Storage {

    private val updateSubject = BehaviorSubject.create<PreferenceUpdate>()
    private val gson = Gson()

    override fun getSubscriptions(channelId: String): List<Subscription>? =
            getPreferences(channelId)?.subscriptions

    override fun getSubscriptions(): List<Subscription>? { //todo this needs to be optimized
        return getPreferencesCollection()
                .find()
                .map { gson.fromJson(it.toJson(), UserPreferences::class.java).subscriptions }
                .filter {
                    it != null && it.isNotEmpty()
                }
                .let { listOfSubscriptions ->
                    if (listOfSubscriptions.isEmpty()) {
                        return null
                    } else {
                        listOfSubscriptions.reduce { list, acc -> list!! + acc!! }
                    }
                }
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
        getPreferencesCollection().deleteMany(ne(PROPERTY_ID, "")) //this will remove all subscriptions
    }

    private fun getPreferences(channelId: String): UserPreferences? =
            getPreferencesCollection()
                    .find(eq(PROPERTY_ID, channelId))
                    .firstOrNull()
                    ?.toJson()
                    ?.let { gson.fromJson(it, UserPreferences::class.java) }

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
    }

}
