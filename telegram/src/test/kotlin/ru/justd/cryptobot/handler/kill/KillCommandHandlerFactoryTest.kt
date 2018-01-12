package ru.justd.cryptobot.handler.kill

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import ru.justd.cryptobot.handler.KillCommandHandlerFactory
import ru.justd.cryptobot.handler.ShutdownException
import ru.justd.cryptobot.messenger.model.Inquiry

@RunWith(MockitoJUnitRunner::class)
class KillCommandHandlerFactoryTest {

    private val uuid = "uuid"
    private val testInstance = KillCommandHandlerFactory(uuid)

    @Test(expected = ShutdownException::class)
    fun testKillThisInstance() {
        tryToKillInstanceWithIdAndGetResponse(uuid)
    }

    @Test
    fun testKillOtherInstance() {
        val message = tryToKillInstanceWithIdAndGetResponse("someshit")
        assertThat(message).isEqualTo("Phew! It's not me!")
    }

    private fun tryToKillInstanceWithIdAndGetResponse(id: String): String {
        return testInstance.create(Inquiry("chatId", false, "lol")).createReply("channelId").text
    }

}