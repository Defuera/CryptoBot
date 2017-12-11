package ru.justd.cryptobot.api.exchanges

data class ShiffrTicker (
        val exchangeName : String,
        val base : String,
        val target : String,
        val price : Double
)