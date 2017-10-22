package ru.justd.cryptobot.handler.help

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.OutgoingMessage

internal object HelpCommandHandler : CommandHandler {

    override fun responseMessage() = OutgoingMessage("todo, restore") //todo
//            Command
//                    .values()
//                    .filter { it.description()?.isNotEmpty() ?: false }
//                    .map { "*${it.scheme}* - ${it.description()}" }
//                    .toList()
//                    .joinToString("\n\n")
//                    .let { OutgoingMessage(it) }
}