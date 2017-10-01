package ru.justd.cryptobot

sealed class RequestHandler(val pattern: String?) {

    object Help : RequestHandler("/help") {
        override fun responseMessage() = "ain't no help for you, doug"
    }

    object Update : RequestHandler("/update") {
        override fun responseMessage() = "ain't no update for you, doug"
    }

    object About : RequestHandler("/about") {
        override fun responseMessage() = "ain't no about for you, doug"
    }

    object Price : RequestHandler("\\/price [A-Z,a-z]{3}\\z") {
        override fun responseMessage() = "ain't no price for you, doug"
    }

    object UnsupportedRequest : RequestHandler(null) {
        override fun responseMessage() = "request is not supported"
    }

    abstract fun responseMessage(): String

    companion object {

        //todo find better why. Now you need to add to the list evety new Handler manually
        private val handlers = listOf(
                Help, Update, About, Price, UnsupportedRequest
        )

        fun findHandler(command: String): RequestHandler =
                handlers.find {
                    it.pattern != null
                            && Regex(it.pattern).matches(command)
                }
                        ?: UnsupportedRequest
    }
}