package ru.justd.cryptobot.handler.price

import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Reply

class PriceClarificatorDelegate(
        private val scheme: String,
        private val exchange: String?,
        private val base: String?,
        private val target: String?
) {
    fun createClarificationRequest(channelId: String): Reply {

        if (exchange == null || exchange.isBlank()) {
            val dialogOptions = arrayOf("Coinbase", "Gdax", "Cryptonator", "Bitfinex")
            return Reply(
                    channelId,
                    "Choose exchange",
                    Dialog(scheme, dialogOptions)
            )
        }

        if (base == null || base.isBlank()) {
            return Reply(
                    channelId,
                    "Choose crypto",
                    Dialog("$scheme $exchange", arrayOf("BTC", "ETH"))
            )
        }

        if (target == null || target.isBlank()) {
            return Reply(
                    channelId,
                    "Choose fiat",
                    Dialog("$scheme $exchange $base", arrayOf("USD", "EUR", "GBP"))
            )
        }

        throw IllegalStateException("Should only be called if one of the args is null")
    }

}