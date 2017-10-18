package ru.justd.cryptobot.messenger

import ru.justd.cryptobot.messaging.model.ResponseCase
import ru.justd.cryptobot.messenger.model.AnswerCase

interface AnswerCaseMapper<in R: ResponseCase> {

    fun map(response: R): AnswerCase

}