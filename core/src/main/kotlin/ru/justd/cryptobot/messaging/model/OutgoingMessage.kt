package ru.justd.cryptobot.messaging.model

data class OutgoingMessage(
        val text: String,
        val responses: Responses<*>? = null
)