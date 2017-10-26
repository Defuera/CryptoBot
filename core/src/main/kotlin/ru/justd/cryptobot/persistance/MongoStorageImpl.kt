package ru.justd.cryptobot.persistance

import com.google.gson.Gson
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.UpdateOptions
import io.reactivex.Observable
import org.bson.Document
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.publisher.Update
import java.util.*
import kotlin.collections.ArrayList

class MongoStorageImpl(private val mongo: MongoDatabase) : Storage {

    override fun getBaseCurrency(channelId: String): String =
            getPreferences(channelId)?.base
                    ?: DEFAULT_BASE_CURRENCY

    override fun setBaseCurrency(channelId: String, base: String) {
        updateProperty(
                channelId,
                { it.copy(base = base) }
        )
    }

    override fun getTargetCurrency(channelId: String): String =
            getPreferences(channelId)?.target
                    ?: DEFAULT_TARGET_CURRENCY

    override fun setTargetCurrency(channelId: String, target: String) {
        updateProperty(
                channelId,
                { it.copy(target = target) }
        )
    }

    override fun getExchangeApi(channelId: String): String =
            getPreferences(channelId)?.exchangeCode
                    ?: DEFAULT_EXCHANGE

    override fun setExchangeApi(channelId: String, exchangeApiName: String) {
        updateProperty(
                channelId,
                { it.copy(exchangeCode = exchangeApiName) }
        )
    }

    override fun getLocale(channelId: String): Locale =
            getPreferences(channelId)?.locale
                    ?: DEFAULT_LOCALE

    override fun setLocale(channelId: String, locale: Locale) {
        updateProperty(
                channelId,
                { it.copy(locale = locale) }
        )
    }

    override fun getSubscriptions(channelId: String): List<Subscription>? =
        getPreferences(channelId)?.subscriptions

    override fun addSubscription(channelId: String, newSubscription: Subscription) {
        updateProperty(
                channelId,
                { it.copy(subscriptions = (it.subscriptions ?: ArrayList()) + newSubscription) }
        )
    }

    override fun observeUpdates(): Observable<Update> = Observable.empty()

    private fun getPreferences(channelId: String): UserPreferences? =
            getPreferencesCollection()
                    .find(eq(PROPERTY_ID, channelId))
                    .firstOrNull()
                    ?.toJson()
                    ?.let { Gson().fromJson(it, UserPreferences::class.java) }

    private fun getPreferencesCollection(): MongoCollection<Document> = mongo.getCollection(COLLECTION_NAME)

    private inline fun updateProperty(channelId: String, preferencesUpdate: (UserPreferences) -> UserPreferences) {
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
