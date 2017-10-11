package ru.justd.cryptobot.handler.kill

import ru.justd.cryptobot.handler.CommandHandler

class KillCommandHandler(
        private val kill: Boolean
) : CommandHandler {

    override fun responseMessage() =
            if (kill)
                throw ShutdownException()
            else
                "Phew! It's not me!"

}
