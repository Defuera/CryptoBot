package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.messenger.model.Reply

interface PurchaseFacade {

    fun transferFunds(channelId: String, address: String, invoicePayload: PurchaseHandler.Payload): Reply

    fun getRate(base: String, target: String) : RateResponse
}