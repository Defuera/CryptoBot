package ru.justd.cryptobot

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.Subscription

internal class SubscribeTest {

    lateinit var testInstance: TelegramCryptAdviser
    private val storageMock = StorageModule.storageMock
    private val userId = "chatId"

    private val BASE_LTC = "LTC"
    private val BASE_BCC = "BCC"
    private val TARGET_GBP = "GBP"
    private val TARGET_EUR = "EUR"
    private val EXCHANGE_GDAX = GdaxApi.NAME
    private val EXCHANGE_CRYPTONATOR = CryptonatorApi.NAME

    @Before
    fun setup() {
        testInstance = TelegramCryptAdviser()
        testInstance.run()

        whenever(storageMock.getExchangeApi(userId)).thenReturn("stub api")
        whenever(storageMock.getBaseCurrency(userId)).thenReturn("stub base")
        whenever(storageMock.getTargetCurrency(userId)).thenReturn("stub target")
    }

    @Test
    fun `test base is absent throws exception`() {
        try {
            //action
            testInstance.handleCommand(userId, "/subscribe")
        } catch (e: Exception) {

            //test
            assertThat(e).isExactlyInstanceOf(InvalidCommand::class.java)
            assertThat(e.message).isEqualTo("/subscribe must be followed by BASE")
        }
    }

    @Test
    fun `test subscribe with ltc gbp`() {
        //action
        val response = testInstance.handleCommand(userId, "/subscribe $TARGET_GBP $TARGET_GBP")

        //test
        assertThat(response).isEqualTo("subscriptions created")
    }

    @Test
    fun `test subscribe btc usd gdax`() {
        //action
        val response = testInstance.handleCommand(userId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_GDAX")

        //test
        assertThat(response).isEqualTo("subscriptions created")
        verify(storageMock).addSubscription(userId, Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5))
    }

    @Test
    fun `test multiple subscriptions`() {
        //action
        testInstance.handleCommand(userId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_GDAX")
        testInstance.handleCommand(userId, "/subscribe $BASE_BCC $TARGET_EUR $EXCHANGE_CRYPTONATOR")

        //test
        verify(storageMock).addSubscription(userId, Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5))
        verify(storageMock).addSubscription(userId, Subscription(BASE_BCC, TARGET_EUR, EXCHANGE_CRYPTONATOR, 5))
    }
}