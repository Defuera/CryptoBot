package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.handler.CommandHandler

class SubscribeHandler constructor(
        private val userPreferences: UserPreferences,
        private val base: String?,
        private val target: String?,
        private val exchange: String?
) : CommandHandler {

    override fun responseMessage(): String {

        try {
            userPreferences.storeSubscription(base, target, exchange) //todo looks like it action should not be result of invoking responseMessage fun..
            //todo notify publisher
            return "subscription created"
        } catch (e: StorageException) {
            return e.message
        }

    }

}