package ru.justd.cryptobot.handler

import java.util.regex.Pattern

enum class Request(val pattern: String) {

    HELP("/help") {

        override fun help(): String = "help hleb"

        override fun handler(): RequestHandler = Help

    },

    UPDATE("/update") {

        override fun help(): String = "helb update"

        override fun handler(): RequestHandler = Update

    },

    ABOUT("/about") {

        override fun help(): String = "about hlep"

        override fun handler(): RequestHandler = About
    },

    PRICE("\\/price [A-Z,a-z]{3}\\z") {

        override fun help(): String = "pricceless hleb"

        override fun handler() = Price.newInstance(pattern)

    };

    abstract fun handler(): RequestHandler

    abstract fun help(): String

    companion object {

        fun handler(command: String) = find(command)?.handler() ?: Unsupported

        private fun find(command: String): Request? =
                values().firstOrNull { Regex(command).matches(it.pattern) }

    }

}