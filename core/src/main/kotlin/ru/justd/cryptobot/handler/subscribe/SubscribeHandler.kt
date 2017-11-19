package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.handler.price.PriceCommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage
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
        val exchangeApiFacade: ExchangeApiFacade,
        val base: String?,
        val target: String?,
        val exchange: String?,
        val period: String?
) : CommandHandler {

    private val PERIOD_1_MINS = "every_1_minute"
    private val PERIOD_5_MINS = "every_5_minutes"
    private val PERIOD_30_MINS = "every_30_minutes"
    private val PERIOD_1_HOUR = "every_hour"
    private val PERIOD_2_HOURS = "every_2_hours"
    private val PERIOD_12_HOURS = "every_12_hours"
    private val PERIOD_1_DAY = "once_a_day"

    private val SET_PERIODS = listOf(
            Option("Every minute", PERIOD_1_MINS),
            Option("Every 5 minutes", PERIOD_5_MINS),
            Option("Every 30 minutes", PERIOD_30_MINS),
            Option("Every hour", PERIOD_1_HOUR),
            Option("Every two hours", PERIOD_2_HOURS),
            Option("Every 12 hours", PERIOD_12_HOURS),
            Option("Every day", PERIOD_1_DAY)
    )

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

        if (period.isNullOrBlank()) {
            return Reply(
                    channelId,
                    "How often do you want to get updates",
                    Dialog("/subscribe $base $target $exchange", SET_PERIODS)
            )
        }


        val priceResponse = PriceCommandHandler(exchangeApiFacade, base, target, exchange).createReply(channelId)

        storage.addSubscription(
                channelId,
                Subscription(
                        UUID.randomUUID().toString(),
                        base,
                        target,
                        exchange,
                        periodToDate(period!!)
                )
        )

        return Reply(channelId, "Subscription created successfully!\n${priceResponse.text}")

    }

    private fun periodToDate(period: String): Long {
        return when (period) {
            PERIOD_1_MINS -> 1
            PERIOD_5_MINS -> 5
            PERIOD_30_MINS -> 30
            PERIOD_1_HOUR -> 60
            PERIOD_2_HOURS -> 120
            PERIOD_12_HOURS -> 60 * 12
            PERIOD_1_DAY -> 60 * 24
            else -> throw InvalidCommand("Unknown period")
        }

    }

}
