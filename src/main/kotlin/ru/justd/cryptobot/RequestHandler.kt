package ru.justd.cryptobot

import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RequestFailedException

sealed class RequestHandler {

    object Help : RequestHandler() {
        /*override*/ fun pattern() = "/help"
        override fun responseMessage() = "ain't no help for you, doug"
    }

    object Update : RequestHandler() {
        /*override*/ fun pattern() = "/update"
        override fun responseMessage() = "ain't no update for you, doug"
    }

    object About : RequestHandler() {
        /*override*/ fun pattern() = "/about"
        override fun responseMessage() = "ain't no about for you, doug"
    }

    class Price private constructor(private val currencyCode: String) : RequestHandler() {
        /*override*/
        companion object {
            fun pattern() = "\\/price [A-Z,a-z]{3}\\z"
            fun newInstance(command: String): RequestHandler {
                return Price(command.takeLast(3)) //todo magic number
            }
        }

        override fun responseMessage(): String {
            try {
                val rate = exchange.getRate(currencyCode)
                return "${rate.base} price is ${rate.amount} ${rate.currency}"
            } catch (error : RequestFailedException){
                return error.message
            }
        }

        private val exchange = ExchangeFacade() //todo inject with dagger based on user preferences?
    }

    object UnsupportedRequest : RequestHandler() {
        override fun responseMessage() = "request is not supported"
    }

    abstract fun responseMessage(): String

    companion object {

        //todo find better way
        fun findHandler(command: String): RequestHandler {
            if (Regex(Help.pattern()).matches(command)) return Help
            if (Regex(Update.pattern()).matches(command)) return Update
            if (Regex(About.pattern()).matches(command)) return About
            if (Regex(Price.pattern()).matches(command)) return Price.newInstance(command)
            return UnsupportedRequest

        }
    }

}