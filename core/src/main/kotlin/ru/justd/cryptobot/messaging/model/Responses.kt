package ru.justd.cryptobot.messaging.model

data class Responses<out R: ResponseCase>(
        val items: List<R>
)