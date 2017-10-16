package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.Subscription

class SubscribeHandler constructor(
        private val id: String,
        private val storage: Storage,
        private val base: String?,
        private val target: String?,
        private val exchange: String?
) : CommandHandler {

    override fun responseMessage(): String {

        //todo looks like it action should not be result of invoking responseMessage fun..
        storage.setSubscription(
                id,
                Subscription(
                        base ?: storage.getBaseCurrency(id),
                        target ?: storage.getTargetCurrency(id),
                        exchange ?: storage.getExchangeApi(id),
                        5
                )
        )
        return "subscription created"

    }

}