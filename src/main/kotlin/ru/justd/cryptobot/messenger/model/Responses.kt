package ru.justd.cryptobot.messenger.model

data class Responses<out R>(
        val items: List<R>
)