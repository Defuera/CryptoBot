package ru.justd.cryptobot.handler.donate

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.messenger.model.Inquiry

class DonateHandlerFactory : CommandHandlerFactory<DonateHandler>("/donate") {
    override fun create(inquiry: Inquiry): DonateHandler {
        val request = trimScheme(inquiry.request)
        return DonateHandler(request)
    }
}