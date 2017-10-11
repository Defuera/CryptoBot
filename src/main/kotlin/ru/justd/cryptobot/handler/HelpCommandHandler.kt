package ru.justd.cryptobot.handler

internal object HelpCommandHandler : CommandHandler {

    override fun responseMessage(): String =
            Command
                    .values()
                    .filter { it.description()?.isNotEmpty() ?: false }
                    .map { "*${it.scheme}* - ${it.description()}" }
                    .toList()
                    .joinToString("\n\n")

}