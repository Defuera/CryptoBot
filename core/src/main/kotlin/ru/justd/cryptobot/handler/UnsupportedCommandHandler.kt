package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.OutgoingMessage

internal object UnsupportedCommandHandler : CommandHandler {

    override fun responseMessage() = OutgoingMessage("request is not supported")

}