package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.Storage

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2
private const val ARG_INDEX_PERIOD = 3

class SubscribeFactory(val storage: Storage) : CommandHandlerFactory<SubscribeHandler>("/subscribe") {

    @Throws(InvalidCommand::class)
    override fun create(channelId: String, request: String): SubscribeHandler {
        println("SubscribeHandler#create $request")

        return SubscribeHandler(
                channelId,
                storage,
                retrieveArg(request, ARG_INDEX_BASE),
                retrieveArg(request, ARG_INDEX_TARGET),
                retrieveArg(request, ARG_INDEX_EXCHANGE),
                retrieveArg(request, ARG_INDEX_PERIOD)
        )
    }

}