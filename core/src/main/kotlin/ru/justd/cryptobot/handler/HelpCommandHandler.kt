package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.OutgoingMessage

internal object HelpCommandHandler : CommandHandler {

    override fun responseMessage() =
            Command
                    .values()
                    .filter { it.description()?.isNotEmpty() ?: false }
                    .map { "*${it.scheme}* - ${it.description()}" }
                    .toList()
                    .joinToString("\n\n")
                    .let { OutgoingMessage(it) }
}