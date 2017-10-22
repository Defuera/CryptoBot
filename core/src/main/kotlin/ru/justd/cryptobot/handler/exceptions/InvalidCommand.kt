package ru.justd.cryptobot.handler.exceptions

class InvalidCommand constructor(override val message: String) : RuntimeException(message)