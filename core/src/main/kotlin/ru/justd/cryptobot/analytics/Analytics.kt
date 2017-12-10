package ru.justd.cryptobot.analytics

interface Analytics {

    fun trackStart(channelId: String)

    fun trackPrice(channelId: String, exchange: String, base: String, target: String)

    fun trackSubscribe(channelId: String, exchange: String, base: String, target: String, period: String)

    fun trackUnsubscribe(channelId: String)

    fun trackAddressInfo(channelId: String)

    fun trackFeedback(channelId: String)

}