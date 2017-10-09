package ru.justd.cryptobot.exchanges.exceptions

class RequestFailed(override val message: String) : RuntimeException(message)