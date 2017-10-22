package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import java.util.*

abstract class CommandHandlerFactory<out T : CommandHandler>(val scheme: String) {

    fun description(): String? = try {
        helpResource.getString(scheme)
    } catch (e: MissingResourceException) {
        ""
    }

    companion object {
        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", Locale.getDefault()) //todo get from preferences
    }

    fun canHandle(request: String) = request.split(" ")[0].toLowerCase() == scheme

    @Throws(InvalidCommand::class)
    abstract fun create(channelId: String, request: String): T

}