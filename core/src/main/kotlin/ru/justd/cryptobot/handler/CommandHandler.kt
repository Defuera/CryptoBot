package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.OutgoingMessage

interface CommandHandler {

    fun responseMessage(): OutgoingMessage

}