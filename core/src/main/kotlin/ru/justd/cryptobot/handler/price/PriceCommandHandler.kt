package ru.justd.cryptobot.handler.price

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply

/**
 * Allows user to retrieve cryptos price from supported exchanges.
 *
 * **Usage:** /price BASE TARGET EXCHANGE_CODE
 * 1. BASE - requierd, crypto currency (BTC, ETH, LTC are supported by most exchanges)
 * 1. TARGET - optional, fiat currency (most of exchanges support USD, EUR, GBP)
 * 1. EXCHANGE_CODE - optional, as for now Gdax, Coinbase and Cryptonator exchanges are supported
 */
class PriceCommandHandler constructor(
        private val analytics: Analytics,
        private val exchangeFacade: ExchangeApiFacade,
        private val base: String?,
        private val target: String?,
        private val exchange: String?
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        val delegate = PriceRetrieverDelegate("/price", exchange, base, target, exchangeFacade)
        if (base == null || target == null || exchange == null) {
            return delegate.createClarificationRequest(channelId)
        }

        val message = try {
            val rate = exchangeFacade.getRate(base, target, exchange)
            "${base.toUpperCase()} price is ${rate.amount} ${target.toUpperCase()} (via $exchange)"
        } catch (error: ExchangeNotSupported) {
            "${error.exchange} exchange not supported"
        } catch (error: RequestFailed) {
            error.message
        }.trim()

        analytics.trackPrice(channelId, exchange, base, target)
        return Reply(channelId, message)
    }

}