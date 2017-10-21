package ru.justd.cryptobot.messanger.model

data class OutgoingMessage(
        val text: String,
        val responses: Responses<*>? = null
)