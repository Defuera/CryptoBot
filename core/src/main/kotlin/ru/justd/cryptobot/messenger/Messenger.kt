package ru.justd.cryptobot.messenger

interface Messenger {

    fun sendMessage(channelId: String, message: String)
}
