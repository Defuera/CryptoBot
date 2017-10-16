package ru.justd.cryptobot.handler

interface CommandHandlerFacade {

    fun createCommandHandler(userId: String, requestMessage: String): CommandHandler

}