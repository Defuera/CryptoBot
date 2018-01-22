package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.handler.price.PriceRetrieverDelegate
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.utils.TimeManager
import ru.justd.cryptobot.utils.UuidGenerator

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
 * Exchange parameter is optional, however cryptoAsset and fiatCurrency are required.
 * You can get an update at a specific time (donno how to implement for telegram, so use UCT)
 * You can get an update every x minutes, hours
 * You can get an update when price changes significantly (can specify percent)
 * You can combine those conditions
 * You can have multiple subscriptions
 *
 */
class SubscribeHandler constructor(
        private val analytics: Analytics,
        private val storage: Storage,
        private val exchangeApiFacade: ExchangeApiFacade,
        private val timeManager: TimeManager,
        private val uuidGenerator: UuidGenerator,
        private val base: String?,
        private val target: String?,
        private val exchange: String?,
        private val period: String?
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        val delegate = PriceRetrieverDelegate("/subscribe", exchange, base, target, exchangeApiFacade)
        if (base == null || target == null || exchange == null) {
            return delegate.createClarificationRequest(channelId)
        }

        if (period == null || period.isBlank()) {
            return Reply(
                    channelId,
                    "How often do you want to get updates",
                    Dialog("/subscribe $exchange $base $target", timeManager.getUpdatePeriods())
            )
        }

        val reply = try {
            val rate = exchangeApiFacade.getRate(base, target, exchange)
            val priceMessage = "${base.toUpperCase()} price is ${rate.amount} ${target.toUpperCase()} (via $exchange)"

            storage.addSubscription(
                    Subscription(
                            uuidGenerator.random(),
                            channelId,
                            base,
                            target,
                            exchange,
                            timeManager.createPublishTimes(System.currentTimeMillis(), period)
                    )
            )

            analytics.trackSubscribe(channelId, exchange, base, target, period)
            "Subscription created successfully!\n$priceMessage"
        } catch (error: ExchangeNotSupported) {
            "${error.exchange} exchange not supported"
        } catch (error: RequestFailed) {
            error.message
        }.trim()

        return Reply(channelId, reply)
    }

}
