package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.Storage
import utils.TimeManager
import utils.UuidGenerator

private const val ARG_INDEX_EXCHANGE = 0
private const val ARG_INDEX_BASE = 1
private const val ARG_INDEX_TARGET = 2
private const val ARG_INDEX_PERIOD = 3

class SubscribeFactory (
        private val analytics: Analytics,
        private val exchangeApiFacade: ExchangeApiFacade,
        private val storage: Storage,
        private val timeManager: TimeManager,
        private val uuidGenerator: UuidGenerator
) : CommandHandlerFactory<SubscribeHandler>("/subscribe") {

    @Throws(InvalidCommand::class)
    override fun create(channelId: String, request: String, private: Boolean): SubscribeHandler {
        println("SubscribeHandler#create $request")

        return SubscribeHandler(
                analytics,
                storage,
                exchangeApiFacade,
                timeManager,
                uuidGenerator,
                retrieveArg(request, ARG_INDEX_BASE),
                retrieveArg(request, ARG_INDEX_TARGET),
                retrieveArg(request, ARG_INDEX_EXCHANGE),
                retrieveArg(request, ARG_INDEX_PERIOD)
        )
    }

}