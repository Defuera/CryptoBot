package ru.justd.cryptobot.api.exchanges.exceptions

class ExchangeNotSupported(val exchange : String) : RuntimeException()