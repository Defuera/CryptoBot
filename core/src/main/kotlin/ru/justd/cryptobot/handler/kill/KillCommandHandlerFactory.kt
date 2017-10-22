package ru.justd.cryptobot.handler.kill

import ru.justd.cryptobot.Bullshit
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandlerFactory

class KillCommandHandlerFactory : CommandHandlerFactory<KillCommandHandler> { //todo move to telegram module

    lateinit var message: String

    override fun create(): KillCommandHandler =
            KillCommandHandler(
                    retrieveInstanceId(message) == Bullshit.INSTANCE_ID
            )

    private fun retrieveInstanceId(message: String) =
            message
                    .replace(Command.KILL.scheme, "")
                    .trim()
                    .split(" ")[0]

}