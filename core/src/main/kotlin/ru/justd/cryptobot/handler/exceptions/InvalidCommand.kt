package ru.justd.cryptobot.handler.exceptions

class InvalidCommand(override val message: String) : RuntimeException(message)