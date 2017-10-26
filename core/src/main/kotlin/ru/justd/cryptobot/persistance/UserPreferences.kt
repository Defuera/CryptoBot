package ru.justd.cryptobot.persistance

import com.google.gson.annotations.SerializedName
import ru.justd.cryptobot.handler.subscribe.Subscription
import java.util.*

data class UserPreferences(
        @SerializedName("base_currency") val base: String? = null,
        @SerializedName("target_currency") val target: String? = null,
        @SerializedName("exchange") val exchangeCode: String? = null,
        @SerializedName("locale") val locale: Locale? = null,
        @SerializedName("subscriptions") val subscriptions: List<Subscription>? = null
)