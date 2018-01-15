package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Inquiry

class KillCommandHandlerFactory(private val instanceUuid : String) : CommandHandlerFactory<KillCommandHandler>("/kill") {

    override fun create(inquiry: Inquiry): KillCommandHandler =
            KillCommandHandler(retrieveInstanceId(inquiry.request) == instanceUuid)

    private fun retrieveInstanceId(message: String) =
            message
                    .replace(scheme, "")
                    .trim()
                    .split(" ")[0]

}