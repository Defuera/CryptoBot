package ru.justd.cryptobot.handler

class InstantFactory<out T : CommandHandler> constructor(scheme : String, private val commandHandler: T) : CommandHandlerFactory<T>(scheme) {

    override fun create(channelId: String, request: String): T = commandHandler
}