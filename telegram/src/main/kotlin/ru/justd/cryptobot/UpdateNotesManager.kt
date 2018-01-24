package ru.justd.cryptobot

import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.messenger.MessageSender
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig
import java.io.File

private const val UPDATE_NOTES_FILE_NAME = "UPDATE_NOTES.md"

class UpdateNotesManager(private val messageSender: MessageSender) {

    fun sendUpdates() {
        launch {
            val version = BuildConfig.VERSION
            val channelsToUpdate = getChannelsToUpdate(version)
            val updateText = getUpdateText(version)
            channelsToUpdate.forEach {
                sendUpdate(it, updateText)
                markAsUpdated(it)
            }

        }
    }

    fun getUpdateText(version: String): String {
        val file = File(UPDATE_NOTES_FILE_NAME)
        var read = false
        var updateText = ""
        file.forEachLine { line: String ->
            if (read) {
                if (line == "") {
                    read = false
                } else {
                    if (!updateText.isEmpty()) {
                        updateText += "\n"
                    }
                    updateText += line
                }
            }

            if (line.contains("v$version:")) {
                read = true
            }
        }
        return updateText
    }

    private fun getChannelsToUpdate(version: String): List<String> {
        return listOf("25954567") //todo
    }

    private fun sendUpdate(channel: String, updateText: String) {
        messageSender.sendMessage(Reply(channel, updateText))
    }

    private fun markAsUpdated(channel: String) {

    }


    /**
     * Compares two version strings.
     *
     * Use this instead of String.compareTo() for a non-lexicographical
     * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
     *
     * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
     *
     * @param str1 a string of ordinal numbers separated by decimal points.
     * @param str2 a string of ordinal numbers separated by decimal points.
     * @return The result is a negative integer if str1 is _numerically_ less than str2.
     * The result is a positive integer if str1 is _numerically_ greater than str2.
     * The result is zero if the strings are _numerically_ equal.
     */
    fun versionCompare(str1: String, str2: String): Int {
        val vals1 = str1.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val vals2 = str2.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var i = 0
        // set index to first non-equal ordinal or length of shortest version string
        while (i < vals1.size && i < vals2.size && vals1[i] == vals2[i]) {
            i++
        }
        // compare first non-equal ordinal number
        if (i < vals1.size && i < vals2.size) {
            val diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]))
            return Integer.signum(diff)
        }
        // the strings are equal or one string is a substring of the other
        // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
        return Integer.signum(vals1.size - vals2.size)
    }
}