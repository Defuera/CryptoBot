package ru.justd.cryptobot.publisher

import ru.justd.cryptobot.persistance.UserPreferences

internal data class Update (
        val userId : String,
        val userPreferences: UserPreferences
)