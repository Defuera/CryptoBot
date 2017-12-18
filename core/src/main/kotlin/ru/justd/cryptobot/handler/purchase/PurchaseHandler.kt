package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Invoice
import ru.justd.cryptobot.messenger.model.Reply

class PurchaseHandler constructor(
        private val exchangeFacade: ExchangeApiFacade,
        private val base: String?
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        return Reply(
                channelId = channelId,
                text = "test",
                invoice = Invoice(
                        100,
                        "usd"
                )
        )
    }

}