package ru.justd.cryptobot.handler.exceptions

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.OutgoingMessage

internal object UnsupportedCommandHandler : CommandHandler {

    override fun responseMessage() = OutgoingMessage("request is not supported")

}