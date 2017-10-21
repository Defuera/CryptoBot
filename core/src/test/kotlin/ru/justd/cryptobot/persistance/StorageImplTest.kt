package ru.justd.cryptobot.persistance

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.subscribe.Subscription


internal class StorageImplTest {

    private val userId = "chatId"

    private val BASE_LTC = "LTC"
    private val BASE_BCC = "BCC"
    private val TARGET_GBP = "GBP"
    private val TARGET_EUR = "EUR"
    private val EXCHANGE_GDAX = GdaxApi.NAME
    private val EXCHANGE_CRYPTONATOR = CryptonatorApi.NAME

    val testInstance = StorageImpl(HashMap())

    @Test
    fun `test multiple subscriptions`() {
        //setup
        val subscription1 = Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5)
        val subscription2 = Subscription(BASE_BCC, TARGET_EUR, EXCHANGE_CRYPTONATOR, 15)

        //action
        testInstance.addSubscription(userId, subscription1)
        testInstance.addSubscription(userId, subscription2)

        //test
        assertThat(testInstance.getSubscriptions(userId)).isEqualTo(listOf(subscription1, subscription2))
    }

}