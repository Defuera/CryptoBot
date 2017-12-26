package ru.justd.cryptobot.handler

/**
 * Factory for simple handlers, which not require runtime configuration.
 * Usually those handlers are singletons (or [object](https://kotlinlang.org/docs/reference/object-declarations.html#object-declarations) in kotlin)
 */
class InstantFactory<out T : CommandHandler> constructor(scheme : String, private val commandHandler: T) : CommandHandlerFactory<T>(scheme) {

    override fun create(channelId: String, request: String, private: Boolean): T = commandHandler

}