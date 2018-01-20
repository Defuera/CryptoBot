package ru.justd.cryptobot.persistance

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.justd.cryptobot.api.exchanges.bitfinex.BitfinexApi
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.Subscription


internal class StorageImplTest {

    private val channelId = "channelId"

    private val BASE_LTC = "LTC"
    private val BASE_BCC = "BCC"
    private val TARGET_GBP = "GBP"
    private val TARGET_EUR = "EUR"
    private val EXCHANGE_GDAX = GdaxApi.NAME
    private val EXCHANGE_BITFINEX = BitfinexApi.NAME

    val testInstance = StorageImpl()

    @Test
    fun `test multiple subscriptions`() {
        //setup
        val subscription1 = Subscription("uuid1", "channelId", TARGET_GBP, EXCHANGE_GDAX, 5, BASE_LTC)
        val subscription2 = Subscription("uuid2", "channelId", TARGET_EUR, EXCHANGE_BITFINEX, 15, BASE_BCC)

        //action
        testInstance.addSubscription(subscription1)
        testInstance.addSubscription(subscription2)

        //test
        assertThat(testInstance.getSubscriptions(channelId)).isEqualTo(listOf(subscription1, subscription2))
    }


    @Test
    fun `test remove subscription`() {
        `test multiple subscriptions`()

        testInstance.removeSubscription(channelId, "uuid2")
        assertThat(testInstance.getSubscriptions(channelId))
                .isEqualTo(listOf(
                        Subscription("uuid1", "channelId", TARGET_GBP, EXCHANGE_GDAX, 5, BASE_LTC)
                ))
    }
}