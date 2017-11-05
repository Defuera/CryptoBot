package ru.justd.cryptotrader.api.cryptonator.model

data class Ticker(
        val base: String,
        val target: String,
        val price: Double,
        val volume: Double,
        val change: Double
)