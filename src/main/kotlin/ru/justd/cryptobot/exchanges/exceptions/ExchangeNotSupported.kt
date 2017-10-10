package ru.justd.cryptobot.exchanges.exceptions

class ExchangeNotSupported(val exchange : String) : RuntimeException()