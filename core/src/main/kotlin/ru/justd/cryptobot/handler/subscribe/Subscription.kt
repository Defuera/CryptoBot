package ru.justd.cryptobot.handler.subscribe

data class Subscription (
        val uuid: String,
        val channelId: String,
        val base: String,
        val target: String,
        val exchange: String,
        /**
         * Time of the day, formatted to HH:mm
         */
        val publishTimes: List<String>
) {
    
    constructor( //todo remove
            uuid: String,
            channelId: String,
            target: String,
            exchange: String,
            publishTime: Long,
            base: String
    ) : this(
            uuid,
            channelId,
            base,
            target,
            exchange,
            listOf()
    )
            
}