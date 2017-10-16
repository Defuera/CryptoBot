package ru.justd.cryptobot

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.doNothing
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.persistance.Subscription

internal class SubscribeTest {


    @Test
    fun testSubscribeBtcUsdGdaxEvery5MinSuccess() {
        //setup
        val userId = "chatId"
        val base = "BTC"
        val target = "USD"
        val exchange = GdaxApi.NAME

        doNothing().`when`(StorageModule.storageMock).setSubscription(userId, Subscription(base, target, exchange, 5))
        val testAdviser = TelegramCryptAdviser()
        testAdviser.run()

        //action
        val response = testAdviser.handleCommand(userId, "/subscribe $base $target $exchange")

        //test
        assertThat(response).isEqualTo("subscription created")
    }

}