package ru.justd.cryptobot.handler

interface CommandHandlerFacade {

    fun createCommandHandler(chatId: String, requestMessage: String): CommandHandler

}