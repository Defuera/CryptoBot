package ru.justd.cryptobot.handler

import ru.justd.cryptobot.UserPreferencesImpl
import ru.justd.cryptobot.handler.kill.KillCommandHandlerFactory
import ru.justd.cryptobot.handler.update.UpdateCommandHandler
import java.util.*

enum class Command(val scheme: String) {

    HELP("/help") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory(HelpCommandHandler)

    },

    UPDATE("/update") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory(UpdateCommandHandler)

    },

    ABOUT("/about") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = InstantFactory(AboutCommandHandler)

    },

    PRICE("/price") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = PriceCommandHandlerFactory()

    },

    KILL("/kill") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = KillCommandHandlerFactory()

    };

    abstract fun factory(): CommandHandlerFactory<CommandHandler>

    internal fun description(): String? = try {
        helpResource.getString(scheme)
    } catch (e: MissingResourceException) {
        ""
    }

    companion object {
        private val preferences = UserPreferencesImpl()
        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", preferences.locale())
    }

}