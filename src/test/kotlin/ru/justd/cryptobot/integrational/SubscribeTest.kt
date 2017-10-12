//package ru.justd.cryptobot.integrational
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Test
//import ru.justd.cryptobot.Main
//import ru.justd.cryptobot.di.MainComponent
//import ru.justd.cryptobot.di.TestMainComponent
//
//internal class SubscribeTest {
//
//    @Test
//    fun testSubscribeBtcUsdGdaxEvery5MinSuccess(){
//        assertThat(Main().handleBotCommand("/subscribe")).isEqualTo("")
//    }
//
//    class MainTest : Main() {
//
//        override fun createComponent(): MainComponent {
//            return DaggerTestMainComponent.builder().build()
//        }
//
//    }
//}