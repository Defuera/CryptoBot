package ru.justd.cryptobot

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.handler.exceptions.InvalidCommand
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.persistance.PreferenceUpdate
import ru.justd.cryptobot.persistance.Storage

internal class SubscribeIntegrationTest {

    lateinit var testInstance: CryptoCore

    private lateinit var storageMock: Storage

    private val userId = "chatId"
    private val BASE_LTC = "LTC"
    private val BASE_BCC = "BCC"
    private val TARGET_GBP = "GBP"
    private val TARGET_EUR = "EUR"
    private val EXCHANGE_GDAX = GdaxApi.NAME
    private val EXCHANGE_CRYPTONATOR = CryptonatorApi.NAME

    @Before
    fun setup() {
        storageMock = StorageModule.storageMock
        whenever(storageMock.observeUpdates()).thenReturn(Observable.create<PreferenceUpdate> { })
        whenever(storageMock.getExchangeApi(userId)).thenReturn("stub api")
        whenever(storageMock.getBaseCurrency(userId)).thenReturn("stub base")
        whenever(storageMock.getTargetCurrency(userId)).thenReturn("stub target")

        testInstance = CryptoCore()
    }

    @Test
    fun `test base is absent throws exception`() {
        try {
            //action
            testInstance.handle(userId, "/subscribe")
        } catch (e: Exception) {

            //test
            assertThat(e).isExactlyInstanceOf(InvalidCommand::class.java)
            assertThat(e.message).isEqualTo("Invalid format, please try `/subscribe BASE TARGER {EXCHANGE}`")
        }
    }

    @Test
    fun `test subscribe with ltc gbp`() {
        //action
        val response = testInstance.handle(userId, "/subscribe $BASE_BCC $TARGET_GBP")

        //test
        assertThat(response).isEqualTo("subscriptions created")
        verify(storageMock).addSubscription(userId, Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5))
    }

    @Test
    fun `test subscribe btc usd gdax`() {
        //action
        val response = testInstance.handle(userId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_CRYPTONATOR")

        //test
        assertThat(response).isEqualTo("subscriptions created")
        verify(storageMock).addSubscription(userId, Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_CRYPTONATOR, 5))
    }

    @Test
    fun `test multiple subscriptions`() {
        //action
        testInstance.handle(userId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_GDAX")
        testInstance.handle(userId, "/subscribe $BASE_BCC $TARGET_EUR $EXCHANGE_CRYPTONATOR")

        //test
        verify(storageMock).addSubscription(userId, Subscription(BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5))
        verify(storageMock).addSubscription(userId, Subscription(BASE_BCC, TARGET_EUR, EXCHANGE_CRYPTONATOR, 5))
    }
}