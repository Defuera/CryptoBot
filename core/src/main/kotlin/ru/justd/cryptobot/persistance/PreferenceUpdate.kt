package ru.justd.cryptobot.persistance

data class PreferenceUpdate constructor(
        val userId: String,
        val userPreferences: UserPreferences
)