package ru.justd.cryptobot.messenger.model

data class Invoice(
        val title : String,
        val description : String,
        /**
         * 10000 will result in 10.00
         */
        val amount: Int,
        val fiatCode: String,
        val payload: String
)