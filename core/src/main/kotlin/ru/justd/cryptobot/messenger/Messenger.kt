package ru.justd.cryptobot.messenger

import ru.justd.cryptobot.messenger.model.OutgoingMessage

interface Messenger {

    fun onRequestReceived()

    fun sendMessage(channelId: String, message: OutgoingMessage)
}
