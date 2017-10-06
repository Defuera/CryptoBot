package ru.justd.cryptobot.handler

interface CommandHandlerFactory<out T : CommandHandler> {

    fun create() : T
}