package ru.justd.cryptobot.messanger

interface Messenger {
    fun sendMessage(channelId: String, outgoingMessage: String)
}
