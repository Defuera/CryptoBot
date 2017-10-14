package ru.justd.cryptobot

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class SubscribeTest {

    @Test
    fun testSubscribeBtcUsdGdaxEvery5MinSuccess() {
        //setup
        val testAdviser = TelegramCryptAdviser()
        testAdviser.run()

        //action
        val response = testAdviser.handleCommand("/subscribe")

        //test
        assertThat(response).isEqualTo("not supported")
    }

}