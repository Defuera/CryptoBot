package utils

interface DateManager {

    fun periodToDateTimesList(currentTimeMillis : Long, periodMillis: Long): List<String>

    fun hasTime(times: List<String>): Boolean
}