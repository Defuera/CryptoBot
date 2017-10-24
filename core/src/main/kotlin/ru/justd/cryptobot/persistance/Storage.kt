package ru.justd.cryptobot.persistance

import io.reactivex.Observable
import ru.justd.cryptobot.handler.subscribe.Subscription
import java.util.*

interface Storage { //todo add removeSubscription

    fun getBaseCurrency(channelId: String): String

    fun setBaseCurrency(channelId: String, base: String)

    fun getTargetCurrency(channelId: String): String

    fun setTargetCurrency(channelId: String, base: String)

    fun getExchangeApi(channelId: String): String

    fun setExchangeApi(channelId: String, exchangeApiName: String)

    fun getLocale(channelId: String): Locale

    fun setLocale(channelId: String, locale: Locale)

    fun getSubscriptions(channelId: String): List<Subscription>?

    fun addSubscription(channelId: String, newSubscription: Subscription)

    fun observeUpdates(): Observable<PreferenceUpdate>

}