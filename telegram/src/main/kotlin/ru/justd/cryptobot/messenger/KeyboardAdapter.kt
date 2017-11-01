package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import com.pengrad.telegrambot.model.request.Keyboard
import ru.justd.cryptobot.messenger.model.Reply

object KeyboardAdapter {

    fun hasOptions(reply: Reply): Boolean {
        return reply.dialogOptions.isNotEmpty()
    }

    fun createKeyboard(reply: Reply): Keyboard { //todo probably we wand smarter algorithm here, like to
        val keyboard: Array<Array<String>> = arrayOf(reply.dialogOptions)
        return mapToTelegramKeyboard(keyboard)
    }

    private fun mapToTelegramKeyboard(keyboard: Array<Array<String>>): Keyboard =
            InlineKeyboardMarkup(
                    *keyboard
                            .map {
                                it.map {
                                    InlineKeyboardButton(it).callbackData("callback $it")
                                }.toTypedArray()
                            }
                            .toTypedArray()
            )

}