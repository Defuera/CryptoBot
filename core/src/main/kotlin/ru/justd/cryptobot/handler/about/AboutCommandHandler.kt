package ru.justd.cryptobot.handler.about

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.OutgoingMessage

internal object AboutCommandHandler : CommandHandler {

    override fun responseMessage() =  OutgoingMessage("ain't no about for you, doug")

}