package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Invoice
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.messenger.model.Reply

class PurchaseHandler constructor(
        private val purchaseApi: PurchaseApi,
        private val request: String?
) : CommandHandler {

    val COEFFICIENT = 1.05

    val TAG_BYE_CRYPTO = "TAG_BYE_CRYPTO"
    val TAG_CREATE_INVOICE = "TAG_CREATE_INVOICE"

    override fun createReply(channelId: String): Reply {

        if (request?.contains(TAG_BYE_CRYPTO) == true) {
            val crypto = retrieveParam(TAG_BYE_CRYPTO)
            return prepareByeCryptoRequest(channelId, crypto, "EUR") //todo target
        }

        if (request?.contains(TAG_CREATE_INVOICE) == true) {
            val amountToCharge = retrieveParam(TAG_CREATE_INVOICE).toInt()
            val base = "BTC" //todo
            val target = "EUR"
            return Reply(
                    channelId = channelId,
                    text = "You are buying for €$amountToCharge",
                    invoice = Invoice(
                            "BTC/ETH/LTC",
                            "pay and be happy",
                            amountToCharge * 100,
                            target,
                            createPayload(base, target, amountToCharge)
                    )
            )
        }

        return Reply(
                channelId = channelId,
                text = "What are you up on?",
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

    private fun createPayload(base: String, target: String, amountToCharge: Int): String {
        return "$base $target $amountToCharge"
    }

    private fun prepareByeCryptoRequest(channelId: String, crypto: String, target: String): Reply {
        val rate = purchaseApi.getRate(crypto, target).amount

        val minAmount = (50.0 / rate) * COEFFICIENT
        return Reply(
                channelId = channelId,
                text = "How much you wanna purchase?",
                dialog = Dialog(
                        "/bye",
                        listOf(
                                Option("Bye $minAmount for €50", "$TAG_CREATE_INVOICE 50"),
                                Option("Bye ${minAmount * 2} for €100", "$TAG_CREATE_INVOICE 100"),
                                Option("Bye ${minAmount * 4} for €200", "$TAG_CREATE_INVOICE 200")
                        )
                )
        )
    }

    private fun retrieveParam(tag: String): String {
        return request!!.replace(tag, "").trim()
    }

}