package ru.justd.cryptobot.handler

enum class Command(val command: String) {

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

    PRICE("/price") {

        override fun description(): String = "pricceless hleb"

        override fun handler() = PriceCommandHandler.newInstance(command)

        override fun argsPattern() = "[A-Z,a-z]{3}\\z"

    };

    abstract fun handler(): CommandHandler

    abstract fun description(): String

    internal open fun argsPattern(): String = ""

    private fun matches(command: String): Boolean {
        val pattern = "${this.command} ${argsPattern()}".trim()
        return Regex(pattern).matches(command)
    }

    companion object {

        fun findCommandHandler(command: String) = find(command)?.handler() ?: UnsupportedCommandHandler

        private fun find(command: String): Command? =
                values().firstOrNull { it.matches(command) }

    }

}