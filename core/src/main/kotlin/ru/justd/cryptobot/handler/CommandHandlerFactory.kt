package ru.justd.cryptobot.handler

import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import java.util.*

abstract class CommandHandlerFactory<out T : CommandHandler>(val scheme: String) {

    companion object {
        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", Locale.getDefault()) //todo get from preferences
    }

    fun description(): String? = try {
        helpResource.getString(scheme)
    } catch (e: MissingResourceException) {
        ""
    }

    fun canHandle(request: String) = request.split(" ")[0].toLowerCase() == scheme

    @Throws(InvalidCommand::class)
    abstract fun create(channelId: String, request: String): T

    fun retrieveArg(request: String, index: Int): String? {
        val args = trimScheme(request).split(" ")

        val argument = if (index <= args.lastIndex) args[index] else null
        return if (argument.isNullOrBlank()){
            null
        } else {
            argument
        }
    }

    fun trimScheme(request: String): String {
        return request
                .replace(scheme, "")
                .trim()
    }

}