package ru.justd.cryptobot.handler

import ru.justd.cryptobot.UserPreferences
import java.util.*

enum class Command(val command: String) {

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

        override fun factory(): CommandHandlerFactory<CommandHandler> = PriceCommandHandlerFactory(command)

        override fun argsPattern() = "[A-Z,a-z]{3}\\z"

    };

    abstract fun factory(): CommandHandlerFactory<CommandHandler>

    internal fun description(): String = helpResource.getString(command)

    protected open fun argsPattern(): String? = null

    private fun matches(command: String): Boolean {
        val pattern = "${this.command} ${argsPattern() ?: ""}".trim()
        return Regex(pattern).matches(command)
    }

    companion object {

        private val preferences = UserPreferences()
        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", preferences.locale())

        fun findCommandHandlerFactory(command: String) = find(command)?.factory() ?: InstantFactory(UnsupportedCommandHandler)

        private fun find(command: String): Command? =
                values().firstOrNull { it.matches(command) }

    }

}