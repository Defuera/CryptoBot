package ru.justd.cryptobot.handler

import ru.justd.cryptobot.UserPreferences
import java.util.*

import ru.justd.cryptobot.exchanges.ExchangeFacade

enum class Command(val command: String) {

    HELP("/help") {

        override fun handler(): CommandHandler = HelpCommandHandler

    },

    UPDATE("/update") {

        override fun handler(): CommandHandler = UpdateCommandHandler

    },

    ABOUT("/about") {

        override fun handler(): CommandHandler = AboutCommandHandler

    },

    PRICE("/price") {

        override fun handler(): CommandHandler = PriceCommandHandler.newInstance(command)

        override fun argsPattern() = "[A-Z,a-z]{3}\\z"

    };

    abstract fun handler(): CommandHandler

    internal fun description(): String = helpResource.getString(command)

    protected open fun argsPattern(): String? = null

    private fun matches(command: String): Boolean {
        val pattern = "${this.command} ${argsPattern() ?: ""}".trim()
        return Regex(pattern).matches(command)
    }

    companion object {

        private val preferences = UserPreferences()
        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", preferences.locale())

        fun findCommandHandler(exchangeFacade: ExchangeFacade, command: String): CommandHandler {
            val handler = find(command)?.handler() ?: UnsupportedCommandHandler
            if (handler is PriceCommandHandler) {
                handler.exchangeFacade = exchangeFacade
            }
            return handler
        }

        private fun find(command: String): Command? =
                values().firstOrNull { it.matches(command) }

    }

}