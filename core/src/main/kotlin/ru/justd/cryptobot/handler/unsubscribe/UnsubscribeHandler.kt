package ru.justd.cryptobot.handler.unsubscribe

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage

class UnsubscribeHandler(
        val channelId: String,
        val storage: Storage,
        val subscriptionId: String?
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        val subscriptions = storage.getSubscriptions(channelId)

        if (subscriptions == null || subscriptions.isEmpty()){
            return Reply(
                    channelId,
                    "You don't have subscriptions yet. To create new subscription use **/subscribe** command"
            )
        }

        if (subscriptionId == null) {
            return Reply(
                    channelId,
                    "Choose subscription to delete:",
                    Dialog("/unsubscribe", subscriptions.map { it.toString() }.toTypedArray())
            )
        }

        storage.removeSubscription(channelId, subscriptionId)
        return Reply(
                channelId,
                "Choose subscription to delete:",
                Dialog("/unsubscribe", subscriptions.map { it.toString() }.toTypedArray())
        )
    }

}