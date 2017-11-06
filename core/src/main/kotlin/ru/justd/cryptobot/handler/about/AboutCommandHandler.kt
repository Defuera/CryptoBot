package ru.justd.cryptobot.handler.about

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply

internal object AboutCommandHandler : CommandHandler {

    override fun createReply(channelId: String) =  Reply(channelId,"ain't no about for you, doug")

}