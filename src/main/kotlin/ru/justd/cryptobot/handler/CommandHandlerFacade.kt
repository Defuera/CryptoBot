package ru.justd.cryptobot.handler

interface CommandHandlerFacade {

    fun createCommandHandler(incomingMessage: String): CommandHandler

}