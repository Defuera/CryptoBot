package utils

import khronos.toString
import java.util.*

object DateManagerImpl : DateManager {

    const val FORMAT_TIME = "HH:mm"

    const val MILLIS_IN_MINUTE = 1000 * 60L
    const val MILLIS_IN_DAY = MILLIS_IN_MINUTE * 60 * 24
    const val ITERATION_PERIOD = MILLIS_IN_MINUTE * 5

    override fun periodToDateTimesList(currentTimeMillis : Long, periodMillis: Long): List<String> {
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

    override fun hasTime(times: List<String>): Boolean {
        val currentTime = Date().toString(FORMAT_TIME)
            return times.contains(currentTime)
    }
}