package ru.justd.cryptobot

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.purchase.PurchaseHandler
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.publisher.Update

interface CryptoCore {

    fun addCommandHandler(commandHandlerFactory: CommandHandlerFactory<*>)

    fun handle(inquiry: Inquiry): Reply

    fun setUpdateListener(listener: (update: Update) -> Unit)

    fun transferFunds(channelId: String, address: String, payload: PurchaseHandler.Payload): Reply

}