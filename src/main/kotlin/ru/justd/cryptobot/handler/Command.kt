package ru.justd.cryptobot.handler

import ru.justd.cryptobot.UserPreferencesImpl
import ru.justd.cryptobot.handler.kill.KillCommandHandlerFactory
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

        override val argsPattern: String
            get() = "[A-Z,a-z]{3}\\z"

    },

    KILL("/kill") {

        override fun factory(): CommandHandlerFactory<CommandHandler> = KillCommandHandlerFactory()

        override val argsPattern: String
            get() = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"

    };

    abstract fun factory(): CommandHandlerFactory<CommandHandler>

    internal fun description(): String? = try {
        helpResource.getString(scheme)
    } catch (e: MissingResourceException) {
        ""
    }

    internal open val argsPattern: String? = null

    companion object {
        private val preferences = UserPreferencesImpl()
        internal val helpResource: ResourceBundle = ResourceBundle.getBundle("help", preferences.locale())
    }

}