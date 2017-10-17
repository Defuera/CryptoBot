package ru.justd.cryptobot.messaging.model

data class Responses<out R>(
        val items: List<R>
)