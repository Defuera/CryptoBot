package ru.justd.cryptobot.handler.price

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2

internal class PriceCommandHandlerFactory(private val exchangeFacade: ExchangeFacade) : CommandHandlerFactory<PriceCommandHandler>("/price") {

    override fun create(channelId: String, request: String): PriceCommandHandler {
        println("PriceCommandHandlerFactory#create $request")
        return PriceCommandHandler(
                exchangeFacade,
                retrieveArg(request, ARG_INDEX_BASE),
                retrieveArg(request, ARG_INDEX_TARGET),
                retrieveArg(request, ARG_INDEX_EXCHANGE)
        )
    }

    private fun retrieveArg(request: String, index: Int): String? {
        val args = request
                .replace(scheme, "")
                .trim()
                .split(" ")

        return if (index <= args.lastIndex) args[index] else null
    }

}