package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Inquiry

internal class PurchaseHandlerFactory(
        private val purchaseFacade: PurchaseFacade,
        private val debug: Boolean
) : CommandHandlerFactory<PurchaseHandler>("/buy") {

    override fun create(inquiry: Inquiry): PurchaseHandler {
        //available for test only
        if (!debug && inquiry.channelId != "25954567") {
            throw InvalidCommand("This feature not yet available in production, please contact us via /feedback to get more info")
        }
        if (!inquiry.private) {
            throw InvalidCommand("Purchase only available via private chat, please talk to me directly")
        }
        return PurchaseHandler(
                purchaseFacade,
                trimScheme(inquiry.request)
        )
    }


}