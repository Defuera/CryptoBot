package ru.justd.cryptobot.handler

internal object HelpCommandHandler : CommandHandler {

    override fun responseMessage(): String {
        val commandDescriptions = Command
                .values()
                .map { "*${it.command}* - ${it.description()}" }
                .toList()

        return commandDescriptions.joinToString("\n\n")
    }

}