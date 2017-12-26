package ru.justd.cryptobot.messenger

import com.pengrad.telegrambot.model.request.InlineKeyboardButton
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Reply

object KeyboardAdapter {

    fun hasOptions(reply: Reply): Boolean {
        return reply.dialog != null
    }

    fun createKeyboard(reply: Reply): InlineKeyboardMarkup {
        val dialog = reply.dialog ?: throw IllegalArgumentException("Dialog must not be null, use hasOptions check before calling this method.")
        return mapToTelegramKeyboard(dialog)
    }

    private fun mapToTelegramKeyboard(dialog: Dialog): InlineKeyboardMarkup {
        val matrix = createButtonsMatrix(dialog)
        return InlineKeyboardMarkup(*matrix)
    }

    private fun createButtonsMatrix(dialog: Dialog): Array<Array<InlineKeyboardButton>> {
        return dialog.dialogOptions
                .map {
                    arrayOf(InlineKeyboardButton(it.name).callbackData("${dialog.callbackLabel} ${it.callbackTag}"))
                }
                .toTypedArray()
    }

}