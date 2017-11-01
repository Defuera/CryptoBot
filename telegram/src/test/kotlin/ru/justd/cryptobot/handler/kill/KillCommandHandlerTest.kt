package ru.justd.cryptobot.handler.kill

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.justd.cryptobot.handler.KillCommandHandler
import ru.justd.cryptobot.handler.ShutdownException

@RunWith(MockitoJUnitRunner::class)
class KillCommandHandlerTest {

    @Test(expected = ShutdownException::class)
    fun testKillThisInstance() {
        val handler = KillCommandHandler(true)
        handler.createReply()
    }

    @Test
    fun testKillOtherInstance() {
        val handler = KillCommandHandler(false)
        assertThat(handler.createReply().text)
                .isEqualTo("Phew! It's not me!")
    }

}