package ru.justd.cryptobot

import khronos.Dates
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.justd.cryptobot.utils.TimeManagerImpl
import ru.justd.cryptobot.utils.TimeManagerImpl.MILLIS_IN_MINUTE


internal class TimeManagerImplTest {

    val testInstance = TimeManagerImpl

    @Test
    fun `test create 12 hours interval`() {
        //set current time to 13:57:12
        val currentTime = Dates.of(2017, 9, 24, 13, 57, 12).time

        //action
        val intervals = testInstance.createPublishTimes(currentTime, MILLIS_IN_MINUTE * 60 * 12)

        //assert
        assertThat(intervals).isEqualTo(listOf("13:57", "01:57"))
    }

    @Test
    fun `test create 30 minutes interval`() {
        //set current time to 9:21:57
        val currentTime = Dates.of(2017, 9, 24, 9, 21, 57).time

        //action
        val intervals = testInstance.createPublishTimes(currentTime, MILLIS_IN_MINUTE * 30)

        //assert
        assertThat(intervals).isEqualTo(
                listOf(
                        "09:21",
                        "09:51",
                        "10:21",
                        "10:51",
                        "11:21",
                        "11:51",
                        "12:21",
                        "12:51",
                        "13:21",
                        "13:51",
                        "14:21",
                        "14:51",
                        "15:21",
                        "15:51",
                        "16:21",
                        "16:51",
                        "17:21",
                        "17:51",
                        "18:21",
                        "18:51",
                        "19:21",
                        "19:51",
                        "20:21",
                        "20:51",
                        "21:21",
                        "21:51",
                        "22:21",
                        "22:51",
                        "23:21",
                        "23:51",
                        "00:21",
                        "00:51",
                        "01:21",
                        "01:51",
                        "02:21",
                        "02:51",
                        "03:21",
                        "03:51",
                        "04:21",
                        "04:51",
                        "05:21",
                        "05:51",
                        "06:21",
                        "06:51",
                        "07:21",
                        "07:51",
                        "08:21",
                        "08:51"
                )
        )
    }

}