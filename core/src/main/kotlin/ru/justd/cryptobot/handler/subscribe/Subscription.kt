package ru.justd.cryptobot.handler.subscribe

data class Subscription constructor(
        val uuid: String,
        val base: String,
        val target: String,
        val exchange: String,
        val periodicityMins: Long
)