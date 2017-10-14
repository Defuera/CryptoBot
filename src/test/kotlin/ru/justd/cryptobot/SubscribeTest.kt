package ru.justd.cryptobot

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.doNothing
import ru.justd.cryptobot.di.StorageModule

internal class SubscribeTest {

    val userPrefMock = StorageModule.userPreferences

    @Test
    fun testSubscribeBtcUsdGdaxEvery5MinSuccess() {
        //setup
        val base = "BTC"
        val target = "USD"
        val exchange = "Coinbase"

        doNothing().`when`(userPrefMock).storeSubscription(base, target, exchange)
        val testAdviser = TelegramCryptAdviser()
        testAdviser.run()

        //action
        val response = testAdviser.handleCommand("/subscribe $base $target $exchange")

        //test
        assertThat(response).isEqualTo("subscription created")
    }

}