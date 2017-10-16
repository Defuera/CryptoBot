package ru.justd.cryptobot

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.doNothing
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.exchanges.gdax.GdaxApi

internal class SubscribeTest {

    val userPrefMock = StorageModule.userPreferences

    @Test
    fun testSubscribeBtcUsdGdaxEvery5MinSuccess() {
        //setup
        val base = "BTC"
        val target = "USD"
        val exchange = GdaxApi.NAME

        doNothing().`when`(userPrefMock).setSubscription(base, target, exchange)
        val testAdviser = TelegramCryptAdviser()
        testAdviser.run()

        //action
        val response = testAdviser.handleCommand("/subscribe $base $target $exchange", message.text())

        //test
        assertThat(response).isEqualTo("subscription created")
    }

}