package ru.justd.cryptobot.adapter

import ru.justd.cryptobot.handler.update.UpdateCase
import ru.justd.cryptobot.messenger.model.OutgoingMessage
import ru.justd.cryptobot.messenger.model.ResponseCase
import ru.justd.cryptobot.messenger.model.AnswerCase
import ru.justd.cryptobot.messenger.model.Message
import ru.justd.cryptobot.messenger.model.UpdateCaseMapper

class MessageAdapter {

    fun map(message: OutgoingMessage): Message =
            Message(
                    message.text,
                    message
                            .responses
                            ?.items
                            ?.map { mapResponses(it) }
                            ?.toTypedArray()
            )

    private fun mapResponses(it: ResponseCase): Array<AnswerCase> {
        return arrayOf(
                when (it) {
                    is UpdateCase -> UpdateCaseMapper().map(it)
                    else -> throw IllegalArgumentException("Unknown response type: ${it::class.java}")
                }
        )
    }

}