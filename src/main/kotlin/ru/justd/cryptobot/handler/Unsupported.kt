package ru.justd.cryptobot.handler

internal object Unsupported : RequestHandler {

    override fun responseMessage() = "request is not supported"

}