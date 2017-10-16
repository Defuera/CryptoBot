package ru.justd.cryptobot.messenger.model

import ru.justd.cryptobot.messenger.model.ResponseCase.Appearance.FULL

enum class UpdateResponseCase : ResponseCase<String> {

    LOCALE {

        override fun appearance(): ResponseCase.Appearance = FULL

        override fun title(): String = "Locale"

    },

    SOMETHING_ELSE {

        override fun appearance(): ResponseCase.Appearance = FULL

        override fun title(): String = "AAAAAAAND ONE MORE THING"

    }

}