package ru.justd.cryptobot.persistance

import ru.justd.cryptobot.handler.subscribe.Subscription

class StorageImpl : Storage {

    private val storageDataSource = mutableListOf<Subscription>()

    @Throws(StorageException::class)
    override fun addSubscription(newSubscription: Subscription) {
        storageDataSource.add(newSubscription)
    }

    override fun removeSubscription(channelId: String, subscriptionId: String) {
        storageDataSource.removeIf { it.channelId == channelId && it.uuid == subscriptionId }
    }

    override fun getSubscriptions(channelId: String): List<Subscription>? = storageDataSource.filter { it.channelId == channelId }

    override fun getAllSubscriptions() = storageDataSource

}
