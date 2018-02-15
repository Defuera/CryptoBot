package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Reply

interface CommandHandler {

    fun createReply(channelId: String): Reply

    fun retrieveParam(request: String, tag: String): String {
        return request.replace(tag, "").trim()
    }

}