//package ru.justd.cryptobot.handler
//
//import ru.justd.cryptobot.handler.about.AboutCommandHandler
//import ru.justd.cryptobot.handler.help.HelpCommandHandler
//import ru.justd.cryptobot.handler.kill.KillCommandHandlerFactory
//import ru.justd.cryptobot.handler.price.PriceCommandHandlerFactory
//import ru.justd.cryptobot.handler.update.UpdateCommandHandler
//import ru.justd.cryptobot.handler.subscribe.SubscribeFactory
//import java.util.*
//
//enum class Command(val scheme: String) {
//
//    HELP("/help") {
//
//        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory("/help", HelpCommandHandler)
//
//    },
//
//    UPDATE("/update") {
//
//        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory("/update", UpdateCommandHandler)
//
//    },
//
//    ABOUT("/about") {
//
//        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory("/about", AboutCommandHandler)
//
//    },
//
//    /**
//     * Allows user to retrieve cryptos price from supported exchanges.
//     *
//     * **Usage:** /price BASE TARGET EXCHANGE_CODE
//     * 1. BASE - requierd, crypto currency (BTC, ETH, LTC are supported by most exchanges)
//     * 1. TARGET - optional, fiat currency (most of exchanges support USD, EUR, GBP)
//     * 1. EXCHANGE_CODE - optional, as for now Gdax, Coinbase and Cryptonator exchanges are supported
//     */
//    PRICE("/price") {
//
//        override fun factory(): CommandHandlerFactory<CommandHandler> = PriceCommandHandlerFactory()
//
//    },
//
//    /**
//     * Provides scheduled updates every x minutes on preconfigured [PRICE] request
//     *
//     * * **Usage:** /subscribe BASE TARGET EXCHANGE_CODE every FREQUENCY_MIN
//     * 1. BASE, TARGET, EXCHANGE_CODE - see [price](Command##Price) //todo link to price command in docs
//     * 1. FREQUENCY_MIN - how often you want to receive updates in minutes.
//     *
//     * You can have multiple subscriptions
//     */
//    SUBSCRIBE("/subscribe") {
//
//        override fun factory(): CommandHandlerFactory<CommandHandler> = SubscribeFactory()
//
//    },
//
//    KILL("/kill") {
//
//        override fun factory(): CommandHandlerFactory<CommandHandler> = KillCommandHandlerFactory()
//
//    };
//
//    abstract fun factory(): CommandHandlerFactory<CommandHandler>
//
////    internal fun description(): String? = try {
////        helpResource.getString(scheme)
////    } catch (e: MissingResourceException) {
////        ""
////    }
////
////    companion object {
////        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", Locale.getDefault()) //todo get from preferences
////    }
//
//}