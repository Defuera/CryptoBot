package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Inquiry

/**
 * Factory for simple handlers, which not require runtime configuration.
 * Usually those handlers are singletons (or [object](https://kotlinlang.org/docs/reference/object-declarations.html#object-declarations) in kotlin)
 */
class InstantFactory<out T : CommandHandler> constructor(scheme : String, private val commandHandler: T) : CommandHandlerFactory<T>(scheme) {

    override fun create(inquiry: Inquiry): T = commandHandler

}