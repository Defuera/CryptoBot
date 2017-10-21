package ru.justd.cryptobot.messanger.model

data class Responses<out R: ResponseCase>(
        val items: List<R>
)