package ru.justd.cryptobot.utils

import java.util.logging.FileHandler
import java.util.logging.Level
import java.util.logging.Logger

object ShiffrLogger {


    val FILE_SIZE = 1024 * 1024
    fun log(tag: String, throwable: Throwable) {
        throwable.printStackTrace()
    }

    fun log(tag: String, message: String) {
        val logText = "[${TimeManagerImpl.readableDateTime()}] $tag: $message"
        println(logText)

//        val fileHandler = FileHandler(TimeManagerImpl.readableDate())
//        val logger = Logger.getGlobal()
//        logger.addHandler(fileHandler)
//        logger.log(Level.INFO, logText)
    }

}