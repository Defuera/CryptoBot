package ru.justd.cryptobot.api.exchanges.gdax.model

/**
 *  {"id":"4d94d046-f06b-4696-9c4e-39cc6801248c","amount":"0.00344097","currency":"BTC"}
 */
data class PurchaseResult (
        val id : String,
        val amount : Double,
        val currency : String
)