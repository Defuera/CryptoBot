package ru.justd.cryptobot.handler

internal object Unsupported : CommandHandler {

    override fun responseMessage() = "request is not supported"

}