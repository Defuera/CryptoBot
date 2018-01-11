package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand

internal class PurchaseHandlerFactory(
        private val purchaseFacade: PurchaseFacade,
        private val debug: Boolean
) : CommandHandlerFactory<PurchaseHandler>("/buy") {

    override fun create(channelId: String, request: String, private: Boolean): PurchaseHandler {
        //available for test only
        if (!debug && channelId != "25954567") {
            throw InvalidCommand("This feature not yet available in production, please contact us via /feedback to get more info")
        }
        if (!private) {
            throw InvalidCommand("Purchase only available via private chat, please talk to me directly")
        }
        return PurchaseHandler(
                purchaseFacade,
                trimScheme(request)
        )
    }


}