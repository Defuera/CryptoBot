package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.CallbackQuery
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity
import com.pengrad.telegrambot.model.Update
import ru.justd.cryptobot.handler.Command
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.handler.CommandHandlerFacade

class MessageReceiver(
        private val commandHandlerFacade: CommandHandlerFacade
) {

    var onProcessListener: ((Long, CommandHandler) -> Unit)? = null

    fun processUpdate(update: Update) {
        val message = update.message()

        if (message != null) {
            handleIncomingMessage(message)
            greetUserIfNeeded(message)
            return
        }

        val callbackQuery = update.callbackQuery()

        if (callbackQuery != null) {
            handleCallbackQuery(callbackQuery)
        }
    }

    private fun handleIncomingMessage(message: Message) {
        message.entities()
                ?.forEach {
                    when (it.type()) {
                        MessageEntity.Type.bot_command -> handleBotCommand(message)
                        else -> println("else message type not supported ${it.type()}")
                    }
                }
    }

    private fun greetUserIfNeeded(message: Message) {
        if (isBotAddedToChannel(message)) {
            val chatId = message.chat().id()
            onProcessListener?.invoke(chatId, Command.ABOUT.factory().create())
            onProcessListener?.invoke(chatId, Command.HELP.factory().create())
        }
    }

    private fun handleCallbackQuery(callbackQuery: CallbackQuery) {

    }

    private fun handleBotCommand(message: Message) { //todo cover with integration test
        val chatId = message.chat().id()
        val handler = commandHandlerFacade.createCommandHandler(chatId.toString(), message.text())
        onProcessListener?.invoke(chatId, handler)
    }

    //todo is there's better way to detect, that telegramBot just been added to a channel/group?
    private fun isBotAddedToChannel(message: Message) =
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

}