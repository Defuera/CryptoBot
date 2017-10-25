package ru.justd.cryptobot.persistance

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import io.reactivex.Observable
import org.bson.Document
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.publisher.Update
import java.util.*

class MongoStorageImpl(private val mongo: MongoDatabase) : Storage {

    override fun registerChannel(channelId: String) {
        if (getPreferences(channelId) == null) {
            getPreferencesCollection().insertOne(
                    createDefaultPreferences(channelId)
            )
        }
    }

    override fun getBaseCurrency(channelId: String): String =
            getPreferences(channelId)
                    ?.getString("base_currency")
                    ?: throw IllegalStateException("channel $channelId wasn't registered")

    override fun setBaseCurrency(channelId: String, base: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTargetCurrency(channelId: String): String =
            getPreferences(channelId)
                    ?.getString("target_currency")
                    ?: throw IllegalStateException("channel $channelId wasn't registered")

    override fun setTargetCurrency(channelId: String, base: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExchangeApi(channelId: String): String =
            getPreferences(channelId)
                    ?.getString("exchange")
                    ?: throw IllegalStateException("channel $channelId wasn't registered")

    override fun setExchangeApi(channelId: String, exchangeApiName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocale(channelId: String): Locale =
            getPreferences(channelId)
                    ?.getString("locale")
                    ?.let { Locale(it) }
                    ?: throw IllegalStateException("channel $channelId wasn't registered")

    override fun setLocale(channelId: String, locale: Locale) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSubscriptions(channelId: String): List<Subscription>? =
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    override fun addSubscription(channelId: String, newSubscription: Subscription) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun observeUpdates(): Observable<Update> = Observable.empty()

    private fun getPreferences(channelId: String): Document? =
            getPreferencesCollection()
                    .find(eq("_id", channelId))
                    .firstOrNull()

    private fun getPreferencesCollection(): MongoCollection<Document> {
        if (!mongo.listCollectionNames().contains("preferences")) {
            mongo.createCollection("preferences")
        }

        return mongo.getCollection("preferences")
    }

    // todo _id must be telegram-agnostic and set incrementally
    private fun createDefaultPreferences(channelId: String) =
            Document("_id", channelId)
                    .append("locale", "en")
                    .append("base_currency", "BTC")
                    .append("target_currency", "USD")
                    .append("exchange", GdaxApi.NAME)

}
