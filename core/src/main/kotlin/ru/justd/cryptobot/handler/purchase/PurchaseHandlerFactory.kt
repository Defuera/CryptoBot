package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory

internal class PurchaseHandlerFactory constructor(private val exchangeFacade: ExchangeApiFacade) : CommandHandlerFactory<PurchaseHandler>("/bye") {

    override fun create(channelId: String, request: String): PurchaseHandler {
        return PurchaseHandler(
                exchangeFacade,
                retrieveArg(request, 0)
        )
    }


}