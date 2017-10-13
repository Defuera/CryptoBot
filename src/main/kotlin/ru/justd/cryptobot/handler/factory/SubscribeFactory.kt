package ru.justd.cryptobot.handler.factory

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.SubscribeHandler

class SubscribeFactory : CommandHandlerFactory<SubscribeHandler> {

    override fun create(): SubscribeHandler {
        return SubscribeHandler()
    }
}