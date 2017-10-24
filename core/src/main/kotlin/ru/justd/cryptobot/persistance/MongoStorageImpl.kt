package ru.justd.cryptobot.persistance

import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import io.reactivex.Observable
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.publisher.Update
import java.util.*

class MongoStorageImpl(val mongo: MongoDatabase) : Storage {

    override fun getBaseCurrency(channelId: String): String =
        mongo.getCollection("preferences")
                .find(eq("id", channelId))
                .first()
                .getString("base_currency")

    override fun setBaseCurrency(channelId: String, base: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTargetCurrency(channelId: String): String =
            mongo.getCollection("preferences")
                    .find(eq("id", channelId))
                    .first()
                    .getString("target_currency")

    override fun setTargetCurrency(channelId: String, base: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExchangeApi(channelId: String): String =
            mongo.getCollection("preferences")
                    .find(eq("id", channelId))
                    .first()
                    .getString("exchange")

    override fun setExchangeApi(channelId: String, exchangeApiName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocale(channelId: String): Locale =
            mongo.getCollection("preferences")
                    .find(eq("id", channelId))
                    .first()
                    .getString("locale")
                    .let { Locale(it) }

    override fun setLocale(channelId: String, locale: Locale) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSubscriptions(channelId: String): List<Subscription>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addSubscription(channelId: String, newSubscription: Subscription) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun observeUpdates(): Observable<Update> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
}