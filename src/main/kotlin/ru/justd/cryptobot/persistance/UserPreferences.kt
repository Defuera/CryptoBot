package ru.justd.cryptobot.persistance

import java.util.*

data class UserPreferences(
        val base: String,
        val target: String,
        val exchangeCode: String,
        val locale: Locale,
        val subscription: Subscription
)