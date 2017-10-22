package ru.justd.cryptobot.handler.kill

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.justd.cryptobot.handler.KillCommandHandlerFactory
import ru.justd.cryptobot.handler.ShutdownException

@RunWith(MockitoJUnitRunner::class)
class KillCommandHandlerFactoryTest {

    @Test(expected = ShutdownException::class)
    fun testKillThisInstance() {
        val id = "uuid"
        tryToKillInstanceWithIdAndGetResponse(id)
    }

    @Test
    fun testKillOtherInstance() {
        val message = tryToKillInstanceWithIdAndGetResponse("someshit")
        assertThat(message).isEqualTo("Phew! It's not me!")
    }

    private fun tryToKillInstanceWithIdAndGetResponse(id: String): String {
        return KillCommandHandlerFactory(id).create("chatId", "/kill $id").responseMessage().text
    }

}