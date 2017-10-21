package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messanger.model.OutgoingMessage

internal object UnsupportedCommandHandler : CommandHandler {

    override fun responseMessage() = OutgoingMessage("request is not supported")

}