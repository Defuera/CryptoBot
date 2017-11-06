package ru.justd.cryptobot.messenger.model

data class Reply constructor(
        val channelId: String,
        val text: String,
        val dialog: Dialog? = null
)