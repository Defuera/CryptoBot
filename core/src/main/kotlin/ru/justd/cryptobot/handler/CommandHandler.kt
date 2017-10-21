package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messanger.model.OutgoingMessage

interface CommandHandler {

    fun responseMessage(): OutgoingMessage

}