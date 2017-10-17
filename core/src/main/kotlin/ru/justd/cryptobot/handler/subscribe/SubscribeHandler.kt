package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messaging.model.OutgoingMessage

class SubscribeHandler constructor(
        private val userPreferences: UserPreferences,
        private val base: String?,
        private val target: String?,
        private val exchange: String?
) : CommandHandler {

    override fun responseMessage(): OutgoingMessage =
            try {
                userPreferences.storeSubscription(base, target, exchange) //todo looks like it action should not be result of invoking responseMessage fun..
                //todo notify publisher
                "subscription created"
            } catch (e: StorageException) {
                e.message
            }.let {
                OutgoingMessage(it)
            }


}