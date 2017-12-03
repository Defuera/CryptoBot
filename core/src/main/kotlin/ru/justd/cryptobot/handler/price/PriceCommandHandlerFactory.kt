package ru.justd.cryptobot.handler.price

import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory

private const val ARG_INDEX_EXCHANGE = 0
private const val ARG_INDEX_BASE = 1
private const val ARG_INDEX_TARGET = 2

internal class PriceCommandHandlerFactory constructor(private val exchangeFacade: ExchangeApiFacade) : CommandHandlerFactory<PriceCommandHandler>("/price") {

    override fun create(channelId: String, request: String): PriceCommandHandler {
        println("PriceCommandHandlerFactory#create $request")
        return PriceCommandHandler(
                exchangeFacade,
                retrieveArg(request, ARG_INDEX_BASE),
                retrieveArg(request, ARG_INDEX_TARGET),
                retrieveArg(request, ARG_INDEX_EXCHANGE)
        )
    }


}