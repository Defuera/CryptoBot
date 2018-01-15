package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.messenger.model.Reply

interface PurchaseFacade {

    fun validateAddress(address: String, base: String): Boolean

    fun transferFunds(channelId: String, address: String, invoicePayload: PurchaseHandler.Payload): Reply

    fun getRate(base: String, target: String) : RateResponse
}