package ru.justd.cryptobot.handler

internal object UnsupportedCommandHandler : CommandHandler {

    override fun responseMessage() = "request is not supported"

}