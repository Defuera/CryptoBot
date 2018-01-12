package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.messenger.model.Reply

internal class CommandHandlerFacadeImpl(
        private val factories: MutableList<CommandHandlerFactory<*>>
) : CommandHandlerFacade {

    @Throws(InvalidCommand::class)
    override fun handle(inquiry: Inquiry): Reply {
        val factory = factories
                .find { it.canHandle(inquiry) }
                ?: throw InvalidCommand("Command `${inquiry.request}` not supported ")

        val handler = factory.create(inquiry)
        return handler.createReply(inquiry.channelId)
    }

    override fun <T : CommandHandler> addCommandHandler(factory: CommandHandlerFactory<T>) {
        factories.add(factory)
    }

}
