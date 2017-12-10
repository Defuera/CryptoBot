package ru.justd.cryptobot.handler.unsubscribe

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage

class UnsubscribeHandler(
        private val analytics: Analytics,
        private val storage: Storage,
        private val subscriptionId: String?
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        val subscriptions = storage.getSubscriptions(channelId)

        if (subscriptions == null || subscriptions.isEmpty()){
            return Reply(
                    channelId,
                    "You don't have subscriptions yet. To create new subscription use **/subscribe** command"
            )
        }

        if (subscriptionId.isNullOrBlank()) {
            return Reply(
                    channelId,
                    "Choose subscription to delete:",
                    Dialog(
                            "/unsubscribe",
                            subscriptions
                                    .map {
                                        Option(
                                                "${it.base} ${it.target} ${it.exchange} ${it.publishTimes}", //todo instead of publishTimes display to user the period
                                                it.uuid
                                        )
                                    }
                    )
            )
        }

        storage.removeSubscription(channelId, subscriptionId!!)
        analytics.trackUnsubscribe(channelId)
        return Reply(
                channelId,
                "Subscription removed successfully"
        )
    }

}