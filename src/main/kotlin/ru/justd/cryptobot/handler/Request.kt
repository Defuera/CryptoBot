package ru.justd.cryptobot.handler

enum class Request(val pattern: String) {

    HELP("/help") {
        override fun handler(): RequestHandler = Help
    },

    UPDATE("/update") {
        override fun handler(): RequestHandler = Update
    },

    ABOUT("/about") {
        override fun handler(): RequestHandler = About
    },

    PRICE("\\/price [A-Z,a-z]{3}\\z/") {
        override fun handler() = Price.newInstance(pattern)
    },

    UNSUPPORTED("(?!.*)") {
        override fun handler(): RequestHandler = Unsupported
    };

    abstract fun handler(): RequestHandler

    companion object {

        fun find(command: String): Request =
                values()
                        .firstOrNull { Regex(command).matches(it.pattern) }
                        ?: UNSUPPORTED

    }

}