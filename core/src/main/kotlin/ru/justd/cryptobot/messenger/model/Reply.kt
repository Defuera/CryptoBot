package ru.justd.cryptobot.messenger.model

data class Reply(
        val text: String,
        val dialogOptions : Array<String> = arrayOf()
)