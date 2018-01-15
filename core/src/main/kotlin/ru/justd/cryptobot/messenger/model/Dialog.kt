package ru.justd.cryptobot.messenger.model

data class Dialog constructor(
        /**
         * should contain most complete dialog request which should be appended by one of the offered options
         */
        val callbackLabel: String,
        val dialogOptions: List<Option>
) {
        constructor(
                callbackTag: String,
                dialogOptions: Array<String>
        ) : this(callbackTag, dialogOptions.map { Option(it, it) })

}

data class Option(
        val name : String,
        val callbackTag: String
)