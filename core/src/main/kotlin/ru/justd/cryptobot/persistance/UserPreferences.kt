package ru.justd.cryptobot.persistance

import com.google.gson.annotations.SerializedName
import ru.justd.cryptobot.handler.subscribe.Subscription

data class UserPreferences(
        @SerializedName("subscriptions") val subscriptions: List<Subscription>? = null
)