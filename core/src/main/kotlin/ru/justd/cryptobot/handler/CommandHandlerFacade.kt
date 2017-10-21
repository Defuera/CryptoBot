package ru.justd.cryptobot.handler

interface CommandHandlerFacade {

    fun createCommandHandler(channelId: String, requestMessage: String): CommandHandler

}