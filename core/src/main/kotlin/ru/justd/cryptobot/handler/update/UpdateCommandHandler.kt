package ru.justd.cryptobot.handler.update

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply


internal object UpdateCommandHandler : CommandHandler {

    override fun createReply(channelId: String) = Reply( //todo, what's that?
            channelId,
            "ain't no update for you, doug"
//            Responses(UpdateCase.values().asList())
    )

}