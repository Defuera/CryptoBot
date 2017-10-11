package ru.justd.cryptobot.handler.kill

import ru.justd.cryptobot.handler.CommandHandler

class KillCommandHandler(
        private val kill: Boolean
) : CommandHandler {

    companion object {

        const val FAREWELL_MESSAGE = "I was glad to serve you! Farewell!"
        const val SURVIVOR_MESSAGE = "Phew! It's not me!"

    }

    override fun responseMessage() =
            if (kill)
                throw ShutdownException()
            else
                SURVIVOR_MESSAGE

}
