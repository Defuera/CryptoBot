package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.OutgoingMessage

internal object AboutCommandHandler : CommandHandler {

    override fun responseMessage() =  OutgoingMessage("ain't no about for you, doug")

}