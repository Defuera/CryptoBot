package ru.justd.cryptobot.messenger.model

data class Responses<out R: ResponseCase>(
        val items: List<R>
)