package ru.justd.cryptobot.persistance

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

class MongoStorageImpl(private val mongo: MongoDatabase) : Storage {

    override fun getBaseCurrency(channelId: String): String =
            getPreferences(channelId)
                    ?.getString(PROPERTY_BASE_CURRENCY)
                    ?: DEFAULT_BASE_CURRENCY

    override fun setBaseCurrency(channelId: String, base: String) {
        updateProperty(
                channelId,
                { it.append(PROPERTY_BASE_CURRENCY, base) }
        )
    }

    override fun getTargetCurrency(channelId: String): String =
            getPreferences(channelId)
                    ?.getString(PROPERTY_TARGET_CURRENCY)
                    ?: DEFAULT_TARGET_CURRENCY

    override fun setTargetCurrency(channelId: String, target: String) {
        updateProperty(
                channelId,
                { it.append(PROPERTY_TARGET_CURRENCY, target) }
        )
    }

    override fun getExchangeApi(channelId: String): String =
            getPreferences(channelId)
                    ?.getString(PROPERTY_EXCHANGE)
                    ?: DEFAULT_EXCHAGE

    override fun setExchangeApi(channelId: String, exchangeApiName: String) {
        updateProperty(
                channelId,
                { it.append(PROPERTY_EXCHANGE, exchangeApiName) }
        )
    }

    override fun getLocale(channelId: String): Locale =
            (getPreferences(channelId)
                    ?.getString(PROPERTY_LOCALE) ?: DEFAULT_LOCALE)
                    .let { Locale(it) }

    override fun setLocale(channelId: String, locale: Locale) {
        updateProperty(
                channelId,
                { it.append(PROPERTY_LOCALE, locale) }
        )
    }

    override fun getSubscriptions(channelId: String): List<Subscription>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addSubscription(channelId: String, newSubscription: Subscription) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun observeUpdates(): Observable<Update> = Observable.empty()

    private fun getPreferences(channelId: String): Document? =
            getPreferencesCollection()
                    .find(eq(PROPERTY_ID, channelId))
                    .firstOrNull()

    private fun getPreferencesCollection(): MongoCollection<Document> = mongo.getCollection(COLLECTION_NAME)

    private inline fun updateProperty(channelId: String, documentUpdate: (Document) -> Document) {
        val update = Document().append(
                "\$set",
                documentUpdate.invoke(Document())
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
        private const val PROPERTY_LOCALE = "locale"
        private const val PROPERTY_BASE_CURRENCY = "base_currency"
        private const val PROPERTY_TARGET_CURRENCY = "target_currency"
        private const val PROPERTY_EXCHANGE = "exchange"
        private const val PROPERTY_SUBSCRIPTIONS = "subscriptions"

        private const val DEFAULT_BASE_CURRENCY = "BTC"
        private const val DEFAULT_TARGET_CURRENCY = "USD"
        private const val DEFAULT_EXCHAGE = GdaxApi.NAME
        private val DEFAULT_LOCALE = Locale.getDefault().language
    }

}
