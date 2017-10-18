package ru.justd.cryptobot.handler.update

import ru.justd.cryptobot.messaging.model.ResponseCase

enum class UpdateCase: ResponseCase {

    LOCALE {
        override fun title(): String = "locale"
    },

    SOMETHING_IMPORTANT {
        override fun title(): String = "something more"
    }

}

