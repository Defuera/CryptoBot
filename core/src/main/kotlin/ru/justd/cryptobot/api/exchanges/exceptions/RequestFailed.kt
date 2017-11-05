package ru.justd.cryptobot.api.exchanges.exceptions

class RequestFailed(override val message: String) : RuntimeException(message)