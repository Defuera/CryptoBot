package ru.justd.cryptobot.persistance

data class Subscription (
        val base: String,
        val target: String,
        val exchange: String,
        val periodicityMins: Long
)