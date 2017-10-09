package ru.justd.cryptobot.handler

internal object HelpCommandHandler : CommandHandler {

    override fun responseMessage(): String =
            Command
                    .values()
                    .map { "*${it.scheme}* - ${it.description()}" }
                    .toList()
                    .joinToString("\n\n")

}