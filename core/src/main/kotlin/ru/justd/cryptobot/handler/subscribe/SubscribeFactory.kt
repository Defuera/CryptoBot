package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.Storage

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2

class SubscribeFactory(val storage: Storage) : CommandHandlerFactory<SubscribeHandler>("/subscribe") {

    @Throws(InvalidCommand::class)
    override fun create(channelId: String, request: String): SubscribeHandler {
        println("SubscribeHandler#create $request")

        val base = retrieveArg(request, ARG_INDEX_BASE)
        val target = retrieveArg(request, ARG_INDEX_TARGET)
        if (base == null || target == null) {
            throw InvalidCommand("Invalid format, please try `/subscribe BASE TARGER {EXCHANGE}`")
        }

        return SubscribeHandler(channelId, storage, base, target, retrieveArg(request, ARG_INDEX_EXCHANGE))
    }

    private fun retrieveArg(request: String, index: Int): String? { //todo this is the same as PriceFactory
        val args = request
                .replace(scheme, "")
                .trim()
                .split(" ")

        return if (index <= args.lastIndex) args[index] else null
    }
}