package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messanger.model.OutgoingMessage
import ru.justd.cryptobot.persistance.Storage


class SubscribeHandler constructor(
        private val userId: String,
        private val storage: Storage,
        val base: String,
        val target: String,
        val exchange: String?
) : CommandHandler {

    override fun responseMessage(): OutgoingMessage {

        //todo looks like it action should not be result of invoking responseMessage fun..
        storage.addSubscription(userId, Subscription(base, target, exchange ?: storage.getExchangeApi(userId), 5))
        return OutgoingMessage("subscriptions created")

    }

}