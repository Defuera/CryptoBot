package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.OutgoingMessage
import ru.justd.cryptobot.persistance.Storage

/**
 * Provides scheduled updates every x minutes on preconfigured [PRICE] request
 *
 * * **Usage:** /subscribe BASE TARGET EXCHANGE_CODE every FREQUENCY_MIN
 * 1. BASE, TARGET, EXCHANGE_CODE - see [price](Command##Price) //todo link to price command in docs
 * 1. FREQUENCY_MIN - how often you want to receive updates in minutes.
 *
 * You can have multiple subscriptions
 */
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