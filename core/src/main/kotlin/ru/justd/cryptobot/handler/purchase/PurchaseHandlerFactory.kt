package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.handler.CommandHandlerFactory

internal class PurchaseHandlerFactory constructor(private val purchaseApi: PurchaseApi) : CommandHandlerFactory<PurchaseHandler>("/buy") {

    override fun create(channelId: String, request: String): PurchaseHandler {
        return PurchaseHandler(
                purchaseApi,
                trimScheme(request)
        )
    }


}