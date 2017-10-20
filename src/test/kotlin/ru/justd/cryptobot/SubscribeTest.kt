package ru.justd.cryptobot

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.persistance.Subscription

internal class SubscribeTest {

    lateinit var testInstance: TelegramCryptAdviser
    private val storageMock = StorageModule.storageMock
    private val userId = "chatId"

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
        //action
        try {
            testInstance.handleCommand(userId, "/subscribe")
        } catch (e: Exception) {

            //test
            assertThat(e).isExactlyInstanceOf(InvalidCommand::class.java)
            assertThat(e.message).isEqualTo("/subscribe must be followed by BASE")
        }

    }

    @Test
    fun testSubscribeBtcSuccess() {
        //setup
        val base = "BTC"

        //action
        val response = testInstance.handleCommand(userId, "/subscribe $base")

        //test
        assertThat(response).isEqualTo("subscriptions created")
    }

    @Test
    fun testSubscribeBtcUsdGdaxEvery5MinSuccess() {
        //setup
        val base = "BTC"
        val target = "USD"
        val exchange = GdaxApi.NAME

        //action
        val response = testInstance.handleCommand(userId, "/subscribe $base $target $exchange")

        //test
        assertThat(response).isEqualTo("subscriptions created")
        verify(storageMock).addSubscription(userId, Subscription(base, target, exchange, 5))
    }
}