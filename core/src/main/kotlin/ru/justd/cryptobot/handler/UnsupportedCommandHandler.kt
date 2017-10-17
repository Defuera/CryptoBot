package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messaging.model.OutgoingMessage

internal object UnsupportedCommandHandler : CommandHandler {

    override fun responseMessage() = OutgoingMessage("request is not supported")

}