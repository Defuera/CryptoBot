package ru.justd.cryptobot.persistance

data class PreferenceUpdate constructor(
        val channelId: String,
        val userPreferences: UserPreferences
)