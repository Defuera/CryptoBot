package ru.justd.cryptobot.handler.trader

import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.robot.SimpleRobot

class TradeHandler(private val exchangeFeedFacade: ExchangeFeedFacade, private val gdaxApi: GdaxApi) : CommandHandler {

    override fun createReply(channelId: String): Reply {

        SimpleRobot(exchangeFeedFacade, gdaxApi)
        return Reply(channelId, "robot activated")
    }

}