package ru.justd.cryptobot.handler.subscribe

data class Subscription constructor(
        val uuid: String,
        val channelId: String,
        val target: String,
        val exchange: String,

        /**
         * Time of the day, formatted to HH:mm
         */
        val publishTimes: List<String>,
        val base: String
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
            target,
            exchange,
            listOf(),
            base
    )
            
}