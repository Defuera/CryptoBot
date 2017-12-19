package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Invoice
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.messenger.model.Reply
import utils.Serializer.deserialize
import utils.Serializer.serialize
import java.math.BigDecimal

class PurchaseHandler constructor(
        private val purchaseApi: PurchaseApi,
        private val request: String?
) : CommandHandler {

    val COEFFICIENT = 1.05

    val TAG_BYE_CRYPTO = "TAG_BYE_CRYPTO"
    val TAG_CREATE_INVOICE = "TAG_CREATE_INVOICE"

    val storeItemsEuro = listOf(50, 100, 200)

    override fun createReply(channelId: String): Reply {

        if (request?.contains(TAG_BYE_CRYPTO) == true) {
            val crypto = retrieveParam(TAG_BYE_CRYPTO)
            return prepareByeCryptoRequest(channelId, crypto, "EUR") //todo target
        }

        if (request?.contains(TAG_CREATE_INVOICE) == true) {
            val payload = deserialize(retrieveParam(TAG_CREATE_INVOICE))
            val title = "Purchase ${payload.baseAmount}${payload.base}"

            return Reply(
                    channelId = channelId,
                    text = title,
                    invoice = Invoice(
                            title,
                            "pay and be happy",
                            payload.targetAmount * 100,
                            payload.target,
                            payload.toString()
                    )
            )
        }

        return Reply(
                channelId = channelId,
                text = "What are you interested in?",
                dialog = Dialog(
                        "/bye",
                        listOf(
                                Option("Bitcoin (BTC)", "$TAG_BYE_CRYPTO BTC"),
                                Option("Ether (ETH)", "$TAG_BYE_CRYPTO ETH"),
                                Option("Litecoin (LTC)", "$TAG_BYE_CRYPTO LTC")
                        )
                )
        )


    }

    private fun prepareByeCryptoRequest(channelId: String, base: String, target: String): Reply {
        val exchangeRate = round(purchaseApi.getRate(base, target).amount, 2)

        return Reply(
                channelId = channelId,
                text = "Current price is $exchangeRate $target. Offer is valid for 5 minutes, please choose item:",
                dialog = Dialog(
                        "/bye",
                        storeItemsEuro.map { targetAmount ->
                            val baseAmount = round(targetAmount * COEFFICIENT / exchangeRate, 8)
                            val encryptedPayload = serialize(Payload(base, baseAmount, target, targetAmount))
                            Option(
                                    "Bye $baseAmount $base for $targetAmount$target",
                                    "$TAG_CREATE_INVOICE $encryptedPayload"
                            )
                        }
                )
        )
    }

    private fun round(num: Double, decimalPlaces: Int) =
            BigDecimal(num).setScale(decimalPlaces, BigDecimal.ROUND_HALF_DOWN).toDouble()


    private fun retrieveParam(tag: String): String {
        return request!!.replace(tag, "").trim()
    }

    data class Payload(
            val base: String,
            val baseAmount: Double,
            val target: String,
            val targetAmount: Int
    )

}