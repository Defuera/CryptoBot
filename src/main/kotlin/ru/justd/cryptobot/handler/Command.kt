package ru.justd.cryptobot.handler

import ru.justd.cryptobot.exchanges.ExchangeFacade

enum class Command(val pattern: String) {

    HELP("/help") {

        override fun description(): String = "description hleb"

        override fun handler(): CommandHandler = HelpCommandHandler

    },

    UPDATE("/update") {

        override fun description(): String = "helb update"

        override fun handler(): CommandHandler = UpdateCommandHandler

    },

    ABOUT("/about") {

        override fun description(): String = "about hlep"

        override fun handler(): CommandHandler = AboutCommandHandler
    },

    PRICE("\\/price [A-Z,a-z]{3}\\z") {

        override fun description(): String = "pricceless hleb"

        override fun handler() = PriceCommandHandler.newInstance(pattern)

    };

    abstract fun handler(): CommandHandler

    abstract fun description(): String

    companion object {

        fun findCommandHandler(exchangeFacade: ExchangeFacade, command: String): CommandHandler {
            val handler = find(command)?.handler() ?: UnsupportedCommandHandler
            if (handler is PriceCommandHandler) {
                handler.exchangeFacade = exchangeFacade
            }
            return handler
        }

        private fun find(command: String): Command? =
                values().firstOrNull { Regex(it.pattern).matches(command) }

    }

}