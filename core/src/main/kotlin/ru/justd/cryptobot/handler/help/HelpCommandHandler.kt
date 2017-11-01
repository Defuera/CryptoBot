package ru.justd.cryptobot.handler.help

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply

internal object HelpCommandHandler : CommandHandler {

    override fun createReply(channelId: String) = Reply(channelId,"todo, restore") //todo
//            Command
//                    .values()
//                    .filter { it.description()?.isNotEmpty() ?: false }
//                    .map { "*${it.scheme}* - ${it.description()}" }
//                    .toList()
//                    .joinToString("\n\n")
//                    .let { Reply(it) }
}