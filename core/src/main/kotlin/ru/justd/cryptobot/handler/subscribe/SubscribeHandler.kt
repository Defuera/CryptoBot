package ru.justd.cryptobot.handler.subscribe

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage

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
class SubscribeHandler(
        private val userId: String,
        private val storage: Storage,
        val base: String?,
        val target: String?,
        val exchange: String?
) : CommandHandler {

    override fun createReply(): Reply {

        if (base.isNullOrBlank()){
            return Reply(
                    "Please choose crypto currency from the list or use full command `/subscribe BASE TARGET EXCHANGE_CODE every INT_TIME_VALUE`", //todo something wrong with /
                    arrayOf("BTC", "ETH", "BCC") //todo magic string. store list of supported currencies
            )
        }

        if (target.isNullOrBlank()){
            return Reply(
                    "Please choose fiat currency from the list or use full command `/subscribe` BASE TARGET EXCHANGE_CODE every INT_TIME_VALUE",
                    arrayOf("USD", "EUR", "GBP") //todo magic string. store list of supported fiat  currencies
            )
        }

        //todo looks like it action should not be result of invoking createReply fun..
        storage.addSubscription(userId, Subscription(base!!, target!!, exchange ?: storage.getExchangeApi(userId), 5))
        return Reply("subscriptions created")

    }

}
