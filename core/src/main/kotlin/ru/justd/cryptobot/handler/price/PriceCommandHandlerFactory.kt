package ru.justd.cryptobot.handler.price

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandlerFactory

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2

class PriceCommandHandlerFactory : CommandHandlerFactory<PriceCommandHandler> {

    lateinit var exchangeFacade: ExchangeFacade

    lateinit var message: String

    override fun create(): PriceCommandHandler { //todo for some reason here's a warning if PriceCommandHandler is internal
        println("PriceCommandHandlerFactory#create $message")
        return PriceCommandHandler(
                exchangeFacade,
                retrieveArg(ARG_INDEX_BASE),
                retrieveArg(ARG_INDEX_TARGET),
                retrieveArg(ARG_INDEX_EXCHANGE)
        )
    }

    private fun retrieveArg(index: Int): String? {
        val args = message
                .replace(Command.PRICE.scheme, "")
                .trim()
                .split(" ")

        return if (index <= args.lastIndex) args[index] else null
    }

}