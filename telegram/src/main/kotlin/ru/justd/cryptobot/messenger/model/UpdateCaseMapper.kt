package ru.justd.cryptobot.messenger.model

import ru.justd.cryptobot.handler.update.UpdateCase
import ru.justd.cryptobot.messenger.AnswerCaseMapper

class UpdateCaseMapper: AnswerCaseMapper<UpdateCase> {

    override fun map(response: UpdateCase): AnswerCase =
            AnswerCase(response.title())

}