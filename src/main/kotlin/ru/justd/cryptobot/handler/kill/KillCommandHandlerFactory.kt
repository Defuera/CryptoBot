package ru.justd.cryptobot.handler.kill

import ru.justd.cryptobot.TelegramCryptAdviser
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandlerFactory

class KillCommandHandlerFactory : CommandHandlerFactory<KillCommandHandler> {

    lateinit var message: String

    override fun create(): KillCommandHandler =
            KillCommandHandler(
                    retrieveInstanceId(message) == TelegramCryptAdviser.INSTANCE_ID
            )

    private fun retrieveInstanceId(message: String) =
            message
                    .replace(Command.KILL.scheme, "")
                    .trim()
                    .split(" ")[0]

}