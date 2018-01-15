package ru.justd.cryptobot.api

import ru.justd.cryptobot.api.exchanges.ExchangeApi
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.messenger.model.Reply

interface PurchaseApi : ExchangeApi {

    @Throws(TransferFailed::class)
    fun transferFunds(channelId: String, base: String, amount: Double, address: String): Reply

}