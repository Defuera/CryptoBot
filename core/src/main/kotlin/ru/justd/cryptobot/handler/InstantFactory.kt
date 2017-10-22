package ru.justd.cryptobot.handler

class InstantFactory<out T : CommandHandler>(private val commandHandler: T) : CommandHandlerFactory<T> {
    override fun create(): T = commandHandler
}