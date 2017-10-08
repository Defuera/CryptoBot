package ru.justd.cryptobot.handler

internal object HelpCommandHandler : CommandHandler {

    override fun responseMessage(): String =
            Command
                    .values()
                    .map { "*${it.literal}* - ${it.description()}" }
                    .toList()
                    .joinToString("\n\n")

}