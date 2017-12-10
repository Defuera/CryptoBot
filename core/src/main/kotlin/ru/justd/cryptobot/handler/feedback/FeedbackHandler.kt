package ru.justd.cryptobot.handler.feedback

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.FeedbackStorage

class FeedbackHandler(
        private val analytics: Analytics,
        private val feedbackStorage: FeedbackStorage,
        private val feedback: String
) : CommandHandler {

    override fun createReply(channelId: String): Reply {
        feedbackStorage.store("feedback from $channelId:\n\n\"$feedback\"")

        analytics.trackFeedback(channelId)
        return Reply(channelId, "Thank you for your feedback!")
    }

}