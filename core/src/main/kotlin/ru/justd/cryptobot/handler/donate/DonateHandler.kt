package ru.justd.cryptobot.handler.donate

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Invoice
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.messenger.model.Reply

class DonateHandler(val request: String) : CommandHandler {

    private val TAG_CREATE_INVOICE = "TAG_CREATE_INVOICE"

    override fun createReply(channelId: String): Reply {
        if (!request.contains(TAG_CREATE_INVOICE)) {
            return Reply(
                    channelId = channelId,
                    text = "Help us do more!\n" +
                            "\n" +
                            "We'll get right to the point: we're asking you to help support ShiffrBot. If everyone reading this gives \$10 monthly, we can continue to thrive for years." +
                            "\n\nBy donating, you agree to our [Terms of service](https://defuera.github.io/CryptoBot#terms_of_service) and [Privacy policy](https://defuera.github.io/CryptoBot#privacy_policy)",


                    dialog = Dialog(
                            "/donate",
                            listOf(
                                    Option("2€", "$TAG_CREATE_INVOICE 2"),
                                    Option("5€", "$TAG_CREATE_INVOICE 5"),
                                    Option("10€", "$TAG_CREATE_INVOICE 10")
                            )
                    )
            )
        } else {
            val amount = retrieveParam(request, TAG_CREATE_INVOICE).toInt()
            return Reply(
                    channelId = channelId,
                    text = "Thank you.",
                    invoice = Invoice(
                            "Dontation to ShiffrBot",
                            "description",
                            amount * 100,
                            "EUR",
                            "payload",
                            false
                    )
            )
        }

    }

}