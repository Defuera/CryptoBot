package ru.justd.cryptobot.handler.feedback

import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.FeedbackStorage

class FeedbackHandler(
        private val feedbackStorage: FeedbackStorage,
        private val feedback: String
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        feedbackStorage.store(feedback)
        return Reply(channelId, "Thank you for your feedback!")
    }

}