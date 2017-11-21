package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.Storage
import utils.TimeManager

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2
private const val ARG_INDEX_PERIOD = 3

class SubscribeFactory (
        private val exchangeApiFacade: ExchangeApiFacade,
        private val storage: Storage,
        private val timeManager: TimeManager
) : CommandHandlerFactory<SubscribeHandler>("/subscribe") {

    @Throws(InvalidCommand::class)
    override fun create(channelId: String, request: String): SubscribeHandler {
        println("SubscribeHandler#create $request") //todo log

        return SubscribeHandler(
                storage,
                exchangeApiFacade,
                timeManager,
                retrieveArg(request, ARG_INDEX_BASE),
                retrieveArg(request, ARG_INDEX_TARGET),
                retrieveArg(request, ARG_INDEX_EXCHANGE),
                retrieveArg(request, ARG_INDEX_PERIOD)
        )
    }

}