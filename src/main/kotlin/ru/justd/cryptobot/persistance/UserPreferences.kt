package ru.justd.cryptobot.persistance

import java.util.*

data class UserPreferences constructor(
        val base: String,
        val target: String,
        val exchangeCode: String,
        val locale: Locale,
        val subscriptions: List<Subscription>
)