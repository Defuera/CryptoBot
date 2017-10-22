package ru.justd.cryptobot.handler

class KillCommandHandlerFactory(private val instanceUuid : String) : CommandHandlerFactory<KillCommandHandler>("/kill") {

    override fun create(channelId: String, request: String) =
            KillCommandHandler(retrieveInstanceId(request) == instanceUuid)

    private fun retrieveInstanceId(message: String) =
            message
                    .replace(scheme, "")
                    .trim()
                    .split(" ")[0]

}