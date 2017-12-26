package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand

internal class PurchaseHandlerFactory constructor(private val purchaseApi: PurchaseApi) : CommandHandlerFactory<PurchaseHandler>("/buy") {

    override fun create(channelId: String, request: String, private: Boolean): PurchaseHandler {
        if (!private) {
            throw InvalidCommand("Purchase only available via private chat, please talk to me directly")
        }
        return PurchaseHandler(
                purchaseApi,
                trimScheme(request)
        )
    }


}