package ru.justd.cryptobot.messenger

import ru.justd.cryptobot.messanger.Messenger
import ru.justd.cryptobot.messanger.model.OutgoingMessage

class MessengerImpl : Messenger {
    lateinit var messageReceiver: MessageReceiver
    lateinit var messageSender: MessageSender

    override fun sendMessage(channelId: String, outgoingMessage: String) {
        messageSender.sendMessage(
                channelId.toLong(),
                OutgoingMessage(outgoingMessage),
                onSuccess = { _, _ -> }
        )
    }
}