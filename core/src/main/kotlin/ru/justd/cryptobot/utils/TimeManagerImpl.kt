package ru.justd.cryptobot.utils

import khronos.toString
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.messenger.model.Option
import java.util.*

object TimeManagerImpl : TimeManager {

    const val READABLE_DATE_TIME = "dd.MM.yy HH:mm"
    const val READABLE_DATE = "dd.MM.yy"
    const val FORMAT_TIME = "HH:mm"

    const val MILLIS_IN_MINUTE = 1000 * 60L
    const val MILLIS_IN_DAY = MILLIS_IN_MINUTE * 60 * 24
    const val ITERATION_PERIOD = MILLIS_IN_MINUTE * 5

    const val PERIOD_1_MINS = "every_1_minute"
    const val PERIOD_5_MINS = "every_5_minutes"
    const val PERIOD_30_MINS = "every_30_minutes"
    const val PERIOD_1_HOUR = "every_hour"
    const val PERIOD_2_HOURS = "every_2_hours"
    const val PERIOD_12_HOURS = "every_12_hours"
    const val PERIOD_1_DAY = "once_a_day"

    val UPDATE_PERIODS = listOf(
//            Option("Every minute", PERIOD_1_MINS),
            Option("Every 5 minutes", PERIOD_5_MINS),
            Option("Every 30 minutes", PERIOD_30_MINS),
            Option("Every hour", PERIOD_1_HOUR),
            Option("Every two hours", PERIOD_2_HOURS),
            Option("Every 12 hours", PERIOD_12_HOURS),
            Option("Every day", PERIOD_1_DAY)
    )

    @Deprecated("get rid of it")
    override fun createPublishTimes(currentTimeMillis : Long, periodMillis: Long): List<String> {
        val list = mutableListOf<String>()

        var iterationTime = 0L
        while (iterationTime < MILLIS_IN_DAY) {
            if (iterationTime % periodMillis == 0L) {
                list.add(Date(currentTimeMillis + iterationTime).toString(FORMAT_TIME))
            }

            iterationTime += ITERATION_PERIOD
        }

        return list.toList()
    }

    override fun createPublishTimes(currentTimeMillis: Long, period: String): List<String> {
        return createPublishTimes(currentTimeMillis, periodToMillis(period))
    }

    override fun isTimeToPublish(subscription: Subscription): Boolean {
        val currentTime = Date().toString(FORMAT_TIME)
            return subscription.publishTimes.contains(currentTime)
    }

    override fun getUpdatesPeriod() = 1 * MILLIS_IN_MINUTE //every minute

    override fun getUpdatePeriods() = UPDATE_PERIODS

    private fun periodToMillis(period: String): Long {
        return when (period) {
            PERIOD_1_MINS -> 1
            PERIOD_5_MINS -> 5
            PERIOD_30_MINS -> 30
            PERIOD_1_HOUR -> 60
            PERIOD_2_HOURS -> 120
            PERIOD_12_HOURS -> 60 * 12
            PERIOD_1_DAY -> 60 * 24
            else -> throw InvalidCommand("Unknown period")
        } * MILLIS_IN_MINUTE

    }

    override fun readableDateTime() = Date().toString(READABLE_DATE_TIME)

    fun readableDate() = Date().toString(READABLE_DATE)

}