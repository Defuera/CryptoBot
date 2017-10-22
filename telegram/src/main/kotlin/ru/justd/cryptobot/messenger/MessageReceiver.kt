package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.UpdatesListener
import com.pengrad.telegrambot.UpdatesListener.CONFIRMED_UPDATES_ALL
import com.pengrad.telegrambot.model.CallbackQuery
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.MessageEntity
import com.pengrad.telegrambot.model.Update
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.handler.kill.ShutdownException
import ru.justd.cryptobot.messenger.model.OutgoingMessage

class MessageReceiver(
        private val commandHandlerFacade: CommandHandlerFacade
) : UpdatesListener {

    var onUpdateProcessed: ((String, OutgoingMessage) -> Unit)? = null

    override fun process(updates: MutableList<Update>): Int {
        updates.forEach {
            launch { processUpdateTry(it) }
        }

        return CONFIRMED_UPDATES_ALL
    }

    private fun processUpdateTry(update: Update) {
        try {
            processUpdate(update)
        } catch (e: ShutdownException) {
//            killInstance(update.message().chat().id())
        } catch (ex: InvalidCommand) {

        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun processUpdate(update: Update) {
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
//        if (isBotAddedToChannel(message)) {
//            val chatId = message.chat().id() //todo telegram should not know about Command's
//            onUpdateProcessed?.invoke(chatId, Command.ABOUT.factory().create().responseMessage())
//            onUpdateProcessed?.invoke(chatId, Command.HELP.factory().create().responseMessage())
//        }
    }

    private fun handleCallbackQuery(callbackQuery: CallbackQuery) {

    }

    private fun handleBotCommand(message: Message) { //todo cover with integration test
        val channelId = toChannelId(message.chat().id())
        try {
            val handler = commandHandlerFacade.createCommandHandler(channelId, message.text())
            onUpdateProcessed?.invoke(channelId, handler.responseMessage())
        } catch (ex: InvalidCommand) {
            onUpdateProcessed?.invoke(channelId, OutgoingMessage(ex.message))
        }
    }

    private fun toChannelId(chatId: Long) = chatId.toString()

    //todo is there's better way to detect, that telegramBot just been added to a channel/group?
    private fun isBotAddedToChannel(message: Message) =
            message.newChatMembers()?.find { user -> user.isBot && user.username() == "CryptAdviserBot" } != null

}