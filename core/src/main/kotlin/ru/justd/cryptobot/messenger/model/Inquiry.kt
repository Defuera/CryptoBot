package ru.justd.cryptobot.messenger.model

data class Inquiry (
        val channelId: String,
        val private: Boolean,
        val request: String
)