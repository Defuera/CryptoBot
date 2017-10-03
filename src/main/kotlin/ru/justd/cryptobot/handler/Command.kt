package ru.justd.cryptobot.handler

enum class Command(val pattern: String) {

    HELP("/help") {

        override fun description(): String = "description hleb"

        override fun handler(): CommandHandler = Help

    },

    UPDATE("/update") {

        override fun description(): String = "helb update"

        override fun handler(): CommandHandler = Update

    },

    ABOUT("/about") {

        override fun description(): String = "about hlep"

        override fun handler(): CommandHandler = About
    },

    PRICE("\\/price [A-Z,a-z]{3}\\z") {

        override fun description(): String = "pricceless hleb"

        override fun handler() = Price.newInstance(pattern)

    };

    abstract fun handler(): CommandHandler

    abstract fun description(): String

    companion object {

        fun findCommandHandler(command: String) = find(command)?.handler() ?: Unsupported

        private fun find(command: String): Command? =
                values().firstOrNull { Regex(it.pattern).matches(command) }

    }

}