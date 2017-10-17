package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messaging.model.OutgoingMessage

interface CommandHandler {

    fun responseMessage(): OutgoingMessage

}