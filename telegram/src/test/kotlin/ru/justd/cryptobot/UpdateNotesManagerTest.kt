package ru.justd.cryptobot

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.mock
import ru.justd.cryptobot.messenger.MessageSender


internal class UpdateNotesManagerTest {

    val testInstance = UpdateNotesManager(mock(MessageSender::class.java))

    @Test
    fun getUpdateText() {

        val updateText = testInstance.getUpdateText("0.0.2")
        assertThat(updateText)
                .isEqualTo("This is a super test text\nYou better believe it!")
    }

    @Test
    fun versionCompare() {
        assertThat(testInstance.versionCompare("1.0", "0.1")).isGreaterThan(0)
        assertThat(testInstance.versionCompare("1.1.2", "1.0.3")).isGreaterThan(0)
        assertThat(testInstance.versionCompare("1.4.2", "2.8.8")).isLessThan(0)
    }
}