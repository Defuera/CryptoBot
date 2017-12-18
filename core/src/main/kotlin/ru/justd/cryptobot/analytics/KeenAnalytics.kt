package ru.justd.cryptobot.analytics

import io.keen.client.java.JavaKeenClientBuilder
import io.keen.client.java.KeenClient
import io.keen.client.java.KeenProject
import ru.justd.cryptobot.CoreConfig
import utils.TimeManager


class KeenAnalytics(
        private val timeManager: TimeManager,
        private val debug: Boolean
) : Analytics {

    //common properties
    private val PROPERTY_CHANNEL_ID = "channel_id"
    private val PROPERTY_DATE_TIME = "date_time"

    private val PROPERTY_EXCHANGE = "exchange"
    private val PROPERTY_BASE = "base"
    private val PROPERTY_TARGET = "target"

    init {
        KeenClient.initialize(JavaKeenClientBuilder().build())
        KeenClient.client().defaultProject = KeenProject(CoreConfig.KEEN_PROJECT_ID, CoreConfig.KEEN_WRITE_KEY, null)
    }

    override fun trackStart(channelId: String) {
        track("start", channelId)
    }

    override fun trackPrice(channelId: String, exchange: String, base: String, target: String) {
        val properties = HashMap<String, Any>()
        properties.put(PROPERTY_EXCHANGE, exchange)
        properties.put(PROPERTY_BASE, base)
        properties.put(PROPERTY_TARGET, target)

        track("price", channelId, properties)
    }

    override fun trackSubscribe(channelId: String, exchange: String, base: String, target: String, period: String) {
        val properties = HashMap<String, Any>()
        properties.put(PROPERTY_EXCHANGE, exchange)
        properties.put(PROPERTY_BASE, base)
        properties.put(PROPERTY_TARGET, target)

        track("subscribe", channelId, properties)
    }

    override fun trackUnsubscribe(channelId: String) {
        track("unsubscribe", channelId)
    }

    override fun trackAddressInfo(channelId: String) {
        track("address_info", channelId)
    }

    override fun trackFeedback(channelId: String) {
        track("feedback", channelId)
    }

    private fun track(
            collection: String,
            channelId: String,
            properties: HashMap<String, Any> = HashMap()
    ) {
        properties.put(PROPERTY_CHANNEL_ID, channelId)
        properties.put(PROPERTY_DATE_TIME, timeManager.readableDateTime())

        if (debug) {
            println("[analytics] $collection: $properties")
        } else {
            KeenClient.client().addEvent(collection, properties)
        }
    }

}
