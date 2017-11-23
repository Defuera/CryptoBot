package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.handler.price.PriceCommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage
import utils.TimeManager
import utils.UuidGenerator
import java.util.*

/**
 *
 * Allows you to subscribe for an update for a specific cryptocurrency price with given fiat and exchange.
 *
 * * **Usage:** /subscribe BASE TARGET EXCHANGE_CODE every INT_TIME_VALUE
 * 1. BASE - cryptocurrency code (like BTC or LTC), **required**
 * 1. TARGET - fiat code (like USD or EUR), **required**
 * 1. EXCHANGE_CODE - exchange name (like Gdax or Bitfinex, see list of available exchanges), **optional**
 * 1. FREQUENCY_MIN - how often you want to receive updates in minutes (ex. 10m) or hours (2h). Minimum periodicity is 5 minutes
 *
 * Exchange parameter is optional, however base and target are required.
 * You can get an update at a specific time (donno how to implement for telegram, so use UCT)
 * You can get an update every x minutes, hours
 * You can get an update when price changes significantly (can specify percent)
 * You can combine those conditions
 * You can have multiple subscriptions
 *
 */
class SubscribeHandler constructor(
        private val storage: Storage,
        private val exchangeApiFacade: ExchangeApiFacade,
        private val timeManager: TimeManager,
        private val uuidGenerator: UuidGenerator,
        val base: String?,
        val target: String?,
        val exchange: String?,
        val period: String?
) : CommandHandler {

    override fun createReply(channelId: String): Reply { //todo magic strings

        if (base == null || base.isNullOrBlank()) {
            return Reply(
                    channelId,
                    "Choose crypto",
                    Dialog("/subscribe", arrayOf("BTC", "ETH", "BCC"))
            )
        }

        if (target == null || target.isNullOrBlank()) {
            return Reply(
                    channelId,
                    "Choose fiat",
                    Dialog("/subscribe $base", arrayOf("USD", "EUR", "GBP"))
            )
        }

        if (exchange == null || exchange.isNullOrBlank()) {
            return Reply(
                    channelId,
                    "Choose exchange",
                    Dialog("/subscribe $base $target", arrayOf("Coinbase", "Gdax", "Cryptonator", "Bitfinex"))
            )
        }

        if (period == null || period.isNullOrBlank()) {
            return Reply(
                    channelId,
                    "How often do you want to get updates",
                    Dialog("/subscribe $base $target $exchange", timeManager.getUpdatePeriods())
            )
        }


        val priceResponse = PriceCommandHandler(exchangeApiFacade, base, target, exchange).createReply(channelId)

        storage.addSubscription(
                channelId,
                Subscription(
                        uuidGenerator.random(),
                        channelId,
                        base,
                        target,
                        exchange,
                        timeManager.createPublishTimes(System.currentTimeMillis(), period)
                )
        )

        return Reply(channelId, "Subscription created successfully!\n${priceResponse.text}")
    }

}
