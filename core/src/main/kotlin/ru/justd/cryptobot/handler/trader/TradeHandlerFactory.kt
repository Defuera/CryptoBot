package ru.justd.cryptobot.handler.trader

import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory

class TradeHandlerFactory(private val exchangeFeedFacade: ExchangeFeedFacade) : CommandHandlerFactory<TradeHandler>("/trade") {

    override fun create(channelId: String, request: String): TradeHandler {

        return TradeHandler(exchangeFeedFacade)
    }

}