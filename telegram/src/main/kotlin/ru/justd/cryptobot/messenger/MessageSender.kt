package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.request.AnswerPreCheckoutQuery
import ru.justd.cryptobot.messenger.model.Reply

interface MessageSender {

    fun sendMessage(reply: Reply)

    fun updateMessage(messageId: Int, reply: Reply)

    fun confirmPreCheckout(answer: AnswerPreCheckoutQuery)
}