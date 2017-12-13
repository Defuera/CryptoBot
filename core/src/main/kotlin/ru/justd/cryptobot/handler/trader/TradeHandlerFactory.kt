package ru.justd.cryptobot.handler.trader

import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.CommandHandlerFactory

class TradeHandlerFactory (
        private val exchangeFeedFacade: ExchangeFeedFacade,
        private val gdaxApi: GdaxApi
) : CommandHandlerFactory<TradeHandler>("/trade") {

    override fun create(channelId: String, request: String): TradeHandler {

        return TradeHandler(exchangeFeedFacade, gdaxApi, trimScheme(request))
    }

}