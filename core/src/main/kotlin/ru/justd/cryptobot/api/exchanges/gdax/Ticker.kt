package ru.justd.cryptobot.api.exchanges.gdax

data class Ticker(
//    "type": "ticker",
        val sequence: Long,
        val product_id: String,
        val price: Double,
        val open_24h: Double,
        val volume_24h: Double,
        val low_24h: Double,
        val high_24h: Double,
        val volume_30d: Double,
        val best_bid: Double,
        val best_ask: Double,
        val side: String,
        val time: String,
        val trade_id: Long,
        val last_size: Double
)