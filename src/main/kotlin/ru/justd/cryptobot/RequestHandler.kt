package ru.justd.cryptobot

import kotlin.reflect.full.isSubclassOf

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
        private val map = RequestHandler::class.nestedClasses
                .filter { klass -> klass.isSubclassOf(RequestHandler::class) }
                .map { klass -> klass.objectInstance }
                .filterIsInstance<RequestHandler>()
                .associateBy { it.pattern }

        fun valueOf(value: String) = map[value] ?: UnsupportedRequest

        fun values() = map.values.toTypedArray()

        fun findHandler(command: String) : RequestHandler = values().find { it.pattern != null && Regex(it.pattern).matches(command) } ?: UnsupportedRequest
    }
}