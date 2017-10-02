package ru.justd.cryptobot.exchanges

class RequestFailedException(override val message: String) : RuntimeException(message)