package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Reply

interface CommandHandler {

    fun createReply(channelId: String): Reply

}