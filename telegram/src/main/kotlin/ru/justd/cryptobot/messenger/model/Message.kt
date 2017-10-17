package ru.justd.cryptobot.messenger.model

data class Message(
        val text: String,
        val answers: Array<Array<AnswerCase>>? = null
)