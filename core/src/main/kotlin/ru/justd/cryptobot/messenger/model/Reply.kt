package ru.justd.cryptobot.messenger.model

data class Reply constructor(
        val replyTo : String,
        val text: String,
        val dialogOptions : Array<String> = arrayOf()
)