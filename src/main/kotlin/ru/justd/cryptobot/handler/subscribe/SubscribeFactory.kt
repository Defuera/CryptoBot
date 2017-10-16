package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.persistance.Storage

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2

class SubscribeFactory : CommandHandlerFactory<SubscribeHandler> {

    lateinit var storage: Storage
    lateinit var message: String
    lateinit var id: String

    override fun create(): SubscribeHandler {
        println("SubscribeHandler#create $message")
        return SubscribeHandler(
                id,
                storage,
                retrieveArg(ARG_INDEX_BASE),
                retrieveArg(ARG_INDEX_TARGET),
                retrieveArg(ARG_INDEX_EXCHANGE)
        )
    }

    private fun retrieveArg(index: Int): String? { //todo this is the same as PriceFactory
        val args = message
                .replace(Command.PRICE.scheme, "")
                .trim()
                .split(" ")

        return if (index <= args.lastIndex) args[index] else null
    }
}