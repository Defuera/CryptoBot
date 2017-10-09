package ru.justd.cryptobot.handler

import ru.justd.cryptobot.UserPreferences
import java.util.*

enum class Command(val scheme: String) {

    HELP("/help") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory(HelpCommandHandler)

    },

    UPDATE("/update") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory(UpdateCommandHandler)

    },

    ABOUT("/about") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory(AboutCommandHandler)

    },

    PRICE("/price") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = PriceCommandHandlerFactory(scheme)

        override fun argsPattern() = "[A-Z,a-z]{3}\\z"

    };

    abstract fun factory(): CommandHandlerFactory<CommandHandler>

    internal fun description(): String = helpResource.getString(scheme)

    internal open fun argsPattern(): String? = null //todo move to property?

    companion object {
        private val preferences = UserPreferences()
        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", preferences.locale())
    }

}