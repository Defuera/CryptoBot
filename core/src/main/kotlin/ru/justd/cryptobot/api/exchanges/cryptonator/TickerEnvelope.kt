package ru.justd.cryptotrader.api.cryptonator.model

data class TickerEnvelope(
        val ticker: Ticker?,
        val success: Boolean,
        val error: String
)