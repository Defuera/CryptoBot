package ru.justd.cryptobot.handler.subscribe

data class Subscription (
        val base: String,
        val target: String,
        val exchange: String,
        val periodicityMins: Long
)