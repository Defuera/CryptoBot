package ru.justd.cryptobot.persistance

import java.util.*

interface Storage { //todo add removeSubscription

    fun getBaseCurrency(id: String): String

    fun setBaseCurrency(id: String, base: String)

    fun getTargetCurrency(id: String): String

    fun setTargetCurrency(id: String, base: String)

    fun getExchangeApi(id: String): String

    fun setExchangeApi(id: String, exchangeApiName: String)

    fun getLocale(id: String): Locale

    fun setLocale(id: String, locale: Locale)

    fun getSubscription(id: String): Subscription?

    fun getSubscriptions(): List<Subscription>

    fun setSubscription(id: String, newSubscription: Subscription)
}