package ru.justd.cryptobot

import khronos.toString
import java.util.*

object DateManager {

    val MILLIS_IN_MINUTE =  1000 * 60
    val MILLIS_IN_DAY = MILLIS_IN_MINUTE * 60 * 24
    val ITERATION_PERIOD = MILLIS_IN_MINUTE * 5

    fun currentTimeMillis() = System.currentTimeMillis()

    fun periodToDateTimesList(currentTimeMillis : Long, periodMillis: Int): List<String> {

        val list = mutableListOf<String>()

        var iterationTime = 0
        while (iterationTime < MILLIS_IN_DAY){
            if (iterationTime % periodMillis == 0){
                list.add(Date(currentTimeMillis + iterationTime).toString("HH:mm"))
            }

            iterationTime += ITERATION_PERIOD
        }

        return list
    }
}