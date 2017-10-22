package ru.justd.cryptobot.messenger

interface Messenger {
    fun sendMessage(channelId: String, outgoingMessage: String)
}
