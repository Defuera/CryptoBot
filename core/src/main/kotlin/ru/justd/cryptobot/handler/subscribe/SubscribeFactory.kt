package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.Storage

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2

class SubscribeFactory : CommandHandlerFactory<SubscribeHandler> {

    lateinit var storage: Storage
    lateinit var message: String
    lateinit var id: String

    @Throws(InvalidCommand::class)
    override fun create(): SubscribeHandler {
        println("SubscribeHandler#create $message")

        val base = retrieveArg(ARG_INDEX_BASE)
        val target = retrieveArg(ARG_INDEX_TARGET)
        if (base == null || target == null){
            throw InvalidCommand("Invalid format, please try `/subscribe BASE TARGER {EXCHANGE}`")
        }

        return SubscribeHandler(id, storage, base, target, retrieveArg(ARG_INDEX_EXCHANGE))
    }

    private fun retrieveArg(index: Int): String? { //todo this is the same as PriceFactory
        val args = message
                .replace(Command.SUBSCRIBE.scheme, "")
                .trim()
                .split(" ")

        return if (index <= args.lastIndex) args[index] else null
    }
}