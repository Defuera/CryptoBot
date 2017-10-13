package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.OutgoingMessage

internal object UpdateCommandHandler : CommandHandler {

    override fun responseMessage() = OutgoingMessage("ain't no update for you, doug")

}