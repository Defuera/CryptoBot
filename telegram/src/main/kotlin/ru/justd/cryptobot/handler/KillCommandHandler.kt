package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Reply

class KillCommandHandler(
        private val kill: Boolean
) : CommandHandler {

    companion object {

        private const val FAREWELL_MESSAGE = "I was glad to serve you! Farewell!"
        private const val SURVIVOR_MESSAGE = "Phew! It's not me!"

    }

    override fun createReply() =
            if (kill)
                throw ShutdownException(FAREWELL_MESSAGE)
            else
                Reply(SURVIVOR_MESSAGE)

}
