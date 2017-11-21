package ru.justd.cryptobot.persistance

import ru.justd.cryptobot.handler.subscribe.Subscription

interface Storage {

    fun addSubscription(channelId: String, newSubscription: Subscription)

    fun removeSubscription(channelId: String, subscriptionId: String)

    fun getSubscriptions(channelId: String): List<Subscription>?

    fun getSubscriptions(): List<Subscription>?

}