package ru.justd.cryptobot.handler.update

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.OutgoingMessage
import ru.justd.cryptobot.messenger.model.Responses


internal object UpdateCommandHandler : CommandHandler {

    override fun responseMessage() = OutgoingMessage(
            "ain't no update for you, doug",
            Responses(UpdateCase.values().asList())
    )

}