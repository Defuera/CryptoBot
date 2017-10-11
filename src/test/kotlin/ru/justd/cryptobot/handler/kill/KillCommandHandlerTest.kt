package ru.justd.cryptobot.handler.kill

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class KillCommandHandlerTest {

    @Test(expected = ShutdownException::class)
    fun testKillThisInstance() {
        val handler = KillCommandHandler(true)
        handler.responseMessage()
    }

    @Test
    fun testKillOtherInstance() {
        val handler = KillCommandHandler(false)
        assertThat(handler.responseMessage())
                .isEqualTo(KillCommandHandler.SURVIVOR_MESSAGE)
    }

}