package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Reply

class AboutHandler  : CommandHandler {
    override fun createReply(channelId: String): Reply {

        return Reply(channelId,
"""
Our privacy policy and legal information can be found here https://defuera.github.io/CryptoBot/
"""
                        .trimMargin()
        )
    }
}