package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.Coin
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Invoice
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.utils.Serializer.deserialize
import ru.justd.cryptobot.utils.Serializer.serialize
import java.math.BigDecimal

class PurchaseHandler constructor(
        private val purchaseFacade: PurchaseFacade,
        private val request: String?
) : CommandHandler {

    val COEFFICIENT = 1.05

    val TAG_INFO = "TAG_INFO"
    val TAG_BUY_CRYPTO = "TAG_BUY_CRYPTO"
    val TAG_CREATE_INVOICE = "TAG_CREATE_INVOICE"

    val OPTION_INFO = Option("Info on purchase process", TAG_INFO)

    val storeItemsEuro = listOf(2, 5, 10, 50, 100, 200)

    override fun createReply(channelId: String): Reply {

        if (request?.contains(TAG_BUY_CRYPTO) == true) {
            val crypto = retrieveParam(TAG_BUY_CRYPTO)
            return prepareByeCryptoRequest(channelId, crypto, "EUR")
        }

        if (request?.contains(TAG_CREATE_INVOICE) == true) {
            val serializedPayload = retrieveParam(TAG_CREATE_INVOICE)
            val payload = deserialize(serializedPayload)
            val title = "Purchase ${payload.baseAmount} ${payload.base}"
            val coin = Coin.valueOf(payload.base)

            return Reply(
                    channelId = channelId,
                    text = "Please specify ${coin.title} address as Receiver Full name on Shipping info page.",
                    invoice = Invoice(
                            title,
                            "Disclaimer: we do not provide any guarantees, please consider purchase on your own risk. Actual delivered amount may differ, cause actual transfer occurs at a moment or receiving money",
                            payload.targetAmount * 100,
                            payload.target,
                            serializedPayload
                    )
            )
        }

        if (request?.contains(TAG_INFO) == true) {
            return Reply(
                    channelId = channelId,
                    text = "Pleas notice, final amount of delivered crypto is calculated at a time of purchase.\n" +
                            "We purchase funds at exchange (Gdax or Bitfinex) and immediately transfer to specified address. Once purchase is complete you can track your funds at blockchain info resource.\n" +
                            "However take into account that exchange can make transfer with delay. You can always contact our support via `/feedback` to get info on your order status",
                    dialog = Dialog(
                            "/buy",
                            listOf(Option("OK", ""))
                    )
            )
        }

        return Reply(
                channelId = channelId,
                text = "What are you interested in? By continuing you agree to out [terms of use](https://defuera.github.io/CryptoBot/)",
                dialog = Dialog(
                        "/buy",
                        listOf(
                                Option("Bitcoin (BTC)", "$TAG_BUY_CRYPTO BTC"),
                                Option("Ether (ETH)", "$TAG_BUY_CRYPTO ETH"),
                                Option("Litecoin (LTC)", "$TAG_BUY_CRYPTO LTC"),
                                OPTION_INFO
                        )
                )
        )


    }

    private fun prepareByeCryptoRequest(channelId: String, base: String, target: String): Reply {
        val exchangeRate = round(purchaseFacade.getRate(base, target).amount, 2)

        return Reply(
                channelId = channelId,
                text = "Exchange price is $exchangeRate $target. To find out more please choose info button.",
                dialog = Dialog(
                        "/buy",
                        storeItemsEuro.map { targetAmount ->
                            val baseAmount = round(targetAmount * COEFFICIENT / exchangeRate, 8)
                            val encryptedPayload = serialize(Payload(base, baseAmount, target, targetAmount))
                            Option(
                                    "Bye $baseAmount $base for $targetAmount$target",
                                    "$TAG_CREATE_INVOICE $encryptedPayload"
                            )
                        }
                                + OPTION_INFO
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