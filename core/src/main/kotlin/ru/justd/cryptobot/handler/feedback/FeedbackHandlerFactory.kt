package ru.justd.cryptobot.handler.feedback

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.persistance.FeedbackStorage

class FeedbackHandlerFactory(
        private val analytics: Analytics,
        private val feedbackStorage: FeedbackStorage
) : CommandHandlerFactory<FeedbackHandler>("/feedback") {

    override fun create(inquiry: Inquiry): FeedbackHandler {
        val feedbackText = trimScheme(inquiry.request)

        if (feedbackText.isBlank()){
            throw InvalidCommand("feedback should no be blank")
        }

        return FeedbackHandler(analytics, feedbackStorage, feedbackText)
    }
}