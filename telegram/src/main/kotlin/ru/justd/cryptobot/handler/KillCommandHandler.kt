package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Reply

class KillCommandHandler(
        private val kill: Boolean
) : CommandHandler {

    companion object {

        private const val FAREWELL_MESSAGE = "I was glad to serve you! Farewell!"
        private const val SURVIVOR_MESSAGE = "Phew! It's not me!"

    }

    override fun createReply(channelId: String) =
            if (kill)
                throw ShutdownException(channelId, FAREWELL_MESSAGE)
            else
                Reply(channelId, SURVIVOR_MESSAGE)

}
