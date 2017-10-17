package ru.justd.cryptobot.messenger

import ru.justd.cryptobot.messenger.model.AnswerCase

interface AnswerCaseMapper<in R> {

    fun map(response: R): AnswerCase

}