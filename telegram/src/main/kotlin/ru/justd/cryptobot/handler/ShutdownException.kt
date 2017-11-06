package ru.justd.cryptobot.handler

class ShutdownException constructor(val channelId : String, override val message: String) : Throwable(message)
