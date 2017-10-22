//package ru.justd.cryptobot.handler.kill
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.junit.MockitoJUnitRunner
//import ru.justd.cryptobot.Bullshit.INSTANCE_ID
//
//@RunWith(MockitoJUnitRunner::class)
//class KillCommandHandlerFactoryTest {
//
//    @Test(expected = ShutdownException::class)
//    fun testKillThisInstance() {
//        tryToKillInstanceWithIdAndGetResponse(INSTANCE_ID)
//    }
//
//    @Test
//    fun testKillOtherInstance() {
//        val message = tryToKillInstanceWithIdAndGetResponse("someshit")
//        assertThat(message).isEqualTo(KillCommandHandler.SURVIVOR_MESSAGE)
//    }
//
//    private fun tryToKillInstanceWithIdAndGetResponse(id: String): String {
//        val factory = KillCommandHandlerFactory().apply { message = "/kill $id" }
//        return factory.create().responseMessage().text
//    }
//
//}