package ru.justd.cryptobot.api.exchanges.gdax.model

data class TransferFailed(
        val channelId: String,
        val base: String,
        val amount: Double,
        val address: String,
        val errorMessage: String) : Throwable(errorMessage)