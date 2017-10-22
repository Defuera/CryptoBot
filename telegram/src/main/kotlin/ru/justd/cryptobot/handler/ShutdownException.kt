package ru.justd.cryptobot.handler

class ShutdownException constructor(override val message: String) : Throwable(message)
