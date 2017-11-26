package ru.justd.cryptobot.persistance

import com.google.gson.Gson
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import ru.justd.cryptobot.handler.subscribe.Subscription

class MongoStorageImpl(private val mongo: MongoDatabase) : Storage {

    private val gson = Gson()

    override fun getSubscriptions(channelId: String): List<Subscription>? =
            getSubscriptionsCollection()
                    .find(eq(PROPERTY_CHANNEL_ID, channelId))
                    .map { gson.fromJson(it.toJson(), Subscription::class.java) }
                    .toList()

    override fun getAllSubscriptions(): List<Subscription>? {
        return getSubscriptionsCollection()
                .find()
                .map { gson.fromJson(it.toJson(), Subscription::class.java) }
                .toList()
    }

    override fun addSubscription(newSubscription: Subscription) {
        getSubscriptionsCollection().insertOne(Document.parse(gson.toJson(newSubscription)))
    }

    override fun removeSubscription(channelId: String, subscriptionId: String) {
        val deleteResult = getSubscriptionsCollection().deleteOne(and(eq(PROPERTY_CHANNEL_ID, channelId), eq(PROPERTY_UUID, subscriptionId)))
        println("removeSubscription, $deleteResult")
    }

    private fun getSubscriptionsCollection(): MongoCollection<Document> = mongo.getCollection(COLLECTION_SUBSCRIPTIONS)

    companion object {
        private const val COLLECTION_SUBSCRIPTIONS = "subs"
        private const val PROPERTY_CHANNEL_ID = "channelId"
        private const val PROPERTY_UUID = "uuid"
    }

}
