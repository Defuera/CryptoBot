package ru.justd.cryptobot.handler.feedback

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.FeedbackStorage

class FeedbackHandlerFactory(private val feedbackStorage: FeedbackStorage) : CommandHandlerFactory<FeedbackHandler>("/feedback") {

    override fun create(channelId: String, request: String): FeedbackHandler {
        val feedbackText = trimScheme(request)

        if (feedbackText.isBlank()){
            throw InvalidCommand("feedback should no be blank")
        }

        return FeedbackHandler(feedbackStorage, feedbackText)
    }
}