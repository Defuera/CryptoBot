package ru.justd.cryptobot.handler.price

import ru.justd.cryptobot.DEFAULT_CURRENCY
import ru.justd.cryptobot.DEFAULT_EXCHANGE
import ru.justd.cryptobot.DEFAULT_FIAT
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory

private const val ARG_INDEX_BASE = 0
private const val ARG_INDEX_TARGET = 1
private const val ARG_INDEX_EXCHANGE = 2

internal class PriceCommandHandlerFactory constructor(private val exchangeFacade: ExchangeApiFacade) : CommandHandlerFactory<PriceCommandHandler>("/price") {

    override fun create(channelId: String, request: String): PriceCommandHandler {
        println("PriceCommandHandlerFactory#create $request")
        return PriceCommandHandler(
                exchangeFacade,
                retrieveArg(request, ARG_INDEX_BASE) ?: DEFAULT_CURRENCY,
                retrieveArg(request, ARG_INDEX_TARGET) ?: DEFAULT_FIAT,
                retrieveArg(request, ARG_INDEX_EXCHANGE) ?: DEFAULT_EXCHANGE
        )
    }


}