package ru.justd.cryptobot.handler.unsubscribe

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.persistance.Storage

private const val ARG_INDEX_SUBSCRIPTION_ID = 0

class UnsubscribeHandlerFactory(
        private val analytics: Analytics,
        val storage: Storage
) : CommandHandlerFactory<UnsubscribeHandler>("/unsubscribe") {

    override fun create(channelId: String, request: String): UnsubscribeHandler {
        println("UnsubscribeHandlerFactory#create $request")

        return UnsubscribeHandler(
                analytics,
                storage,
                retrieveArg(request, ARG_INDEX_SUBSCRIPTION_ID)
        )
    }
}