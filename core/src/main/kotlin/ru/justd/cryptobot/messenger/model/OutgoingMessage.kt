package ru.justd.cryptobot.messenger.model

data class OutgoingMessage(
        val text: String,
        val responses: Responses<*>? = null
)