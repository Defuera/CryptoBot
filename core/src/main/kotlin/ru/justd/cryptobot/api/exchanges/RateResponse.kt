package ru.justd.cryptobot.api.exchanges

data class RateResponse(val amount: Double, val cryptoAsset: String, val fiatCurrency: String)