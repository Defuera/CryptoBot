package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.messenger.model.Reply

/**
 * Aggregates PurchaseApi implementations
 */
class PurchaseFacadeImpl(
        private val purchaseApi: PurchaseApi
) : PurchaseFacade {

    @Throws(TransferFailed::class)
    override fun transferFunds(channelId: String, address: String, invoicePayload: PurchaseHandler.Payload): Reply {
        return purchaseApi.transferFunds(channelId, invoicePayload.base, invoicePayload.baseAmount, address)
    }

    override fun getRate(base: String, target: String) = purchaseApi.getRate(base, target)

}