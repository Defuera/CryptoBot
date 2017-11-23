package utils

import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.messenger.model.Option

interface TimeManager {

    fun createPublishTimes(currentTimeMillis : Long, periodMillis: Long): List<String>

    fun createPublishTimes(currentTimeMillis: Long, period: String): List<String>

    fun isTimeToPublish(subscription: Subscription): Boolean

    fun getUpdatesPeriod(): Long

    fun getUpdatePeriods(): List<Option>

}