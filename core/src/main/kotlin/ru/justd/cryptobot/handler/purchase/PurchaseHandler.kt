package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Invoice
import ru.justd.cryptobot.messenger.model.Reply

/**
 * Allows user to retrieve cryptos price from supported exchanges.
 *
 * **Usage:** /price BASE TARGET EXCHANGE_CODE
 * 1. BASE - requierd, crypto currency (BTC, ETH, LTC are supported by most exchanges)
 * 1. TARGET - optional, fiat currency (most of exchanges support USD, EUR, GBP)
 * 1. EXCHANGE_CODE - optional, as for now Gdax, Coinbase and Cryptonator exchanges are supported
 */
class PurchaseHandler constructor(
        private val exchangeFacade: ExchangeApiFacade,
        private val base: String?
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        return Reply(
                channelId = channelId,
                text = "test",
                invoice = Invoice(
                        10.0,
                        "usd"
                )
        )
    }

}