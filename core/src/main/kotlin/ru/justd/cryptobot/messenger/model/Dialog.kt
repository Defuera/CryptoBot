package ru.justd.cryptobot.messenger.model

data class Dialog constructor(
        /**
         * should contain most complete dialog request which should be appended by one of the offered options
         */
        val callbackLabel: String,
        val dialogOptions: Array<String>
)