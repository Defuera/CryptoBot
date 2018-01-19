package ru.justd.cryptobot.utils

import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter


object LogUtils {

    private const val MAX_FILE_SIZE_BYTES = 1024 * 1024
    private const val FILES_TO_ROTATE_COUNT = 100

    val LOGGER = Logger.getLogger("TelegramBot")

    var debug: Boolean = true
        set(value) {
            field = value
            if (!debug) {
                val fileHandler = FileHandler("logs", MAX_FILE_SIZE_BYTES, FILES_TO_ROTATE_COUNT)
                fileHandler.formatter = SimpleFormatter()
                LOGGER.addHandler(fileHandler)
            }
        }

}
