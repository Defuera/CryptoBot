package ru.justd.cryptobot.handler

import java.util.*

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

        override fun handler() = PriceCommandHandler.newInstance(command)

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

        //todo obtain locale from preferences
        val helpResource: ResourceBundle = ResourceBundle.getBundle("help", Locale.getDefault())

        fun findCommandHandler(command: String) = find(command)?.handler() ?: UnsupportedCommandHandler

        private fun find(command: String): Command? =
                values().firstOrNull { it.matches(command) }

    }

}