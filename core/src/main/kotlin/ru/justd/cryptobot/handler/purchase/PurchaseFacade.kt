package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.api.blockchain.bitcoin.BitcoinAddressValidator
import ru.justd.cryptobot.api.blockchain.ether.EtherAddressValidator
import ru.justd.cryptobot.api.blockchain.litecoin.LitecoinAddressValidator
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.utils.ShiffrLogger

/**
 * Agregates PurchaseApi implementations
 */
class PurchaseFacade(
        private val purchaseApi: PurchaseApi
) {

    fun validateAddress(address: String, base: String): Boolean {
        return when (base.toUpperCase()) {
            "BTC" -> BitcoinAddressValidator.validateAddress(address)
            "ETH" -> EtherAddressValidator.validateAddress(address)
            "LTC" -> LitecoinAddressValidator.validateAddress(address)
            else -> throw IllegalArgumentException("cannot validate $base")
        }
    }

    @Throws(TransferFailed::class)
    fun transferFunds(channelId: String, address: String, invoicePayload: PurchaseHandler.Payload): Reply {
        ShiffrLogger.log("tag", "$address, $invoicePayload")
        return purchaseApi.transferFunds(channelId, invoicePayload.base, invoicePayload.baseAmount, address)
    }

    fun getRate(base: String, target: String) = purchaseApi.getRate(base, target)

}