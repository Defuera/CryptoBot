package ru.justd.cryptobot.handler.trader

import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.robot.SimpleRobot

class TradeHandler(private val exchangeFeedFacade: ExchangeFeedFacade) : CommandHandler {

    override fun createReply(channelId: String): Reply {

        SimpleRobot(exchangeFeedFacade)
        return Reply(channelId, "robot activated")
    }

}