package ru.justd.cryptobot.handler.purchase

import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.messenger.model.Inquiry

internal class PurchaseHandlerFactory(
        private val purchaseFacade: PurchaseFacade,
        private val debug: Boolean
) : CommandHandlerFactory<PurchaseHandler>("/buy") {

//    413470 Иван
//    25954567 Денис (я)
//    135840403 Тимо
//    63838729 Вася
//    246025398 Егор
//    196718279 Томми
//    620708 Леша
//    72639188 Max

    val testChannels = arrayOf(
            "55252078", //Iliya
            "25954567", //Denis
            "63838729", //Vasya
            "72639188"  //Max
    )

    override fun create(inquiry: Inquiry): PurchaseHandler {
        //available for test only
        if (!testChannels.contains(inquiry.channelId)) {
            throw InvalidCommand("This feature not yet available in production, please contact us via /feedback to get more info")
        }
        if (!inquiry.private) {
            throw InvalidCommand("Purchase only available via private chat, please talk to me directly")
        }
        return PurchaseHandler(
                purchaseFacade,
                trimScheme(inquiry.request)
        )
    }


}