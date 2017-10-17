package ru.justd.cryptobot.handler

interface CommandHandlerFacade {

    fun createCommandHandler(requestMessage: String): CommandHandler

}