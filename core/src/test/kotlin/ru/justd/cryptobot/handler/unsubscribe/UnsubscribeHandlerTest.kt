package ru.justd.cryptobot.handler.unsubscribe

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.api.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.persistance.PreferenceUpdate
import ru.justd.cryptobot.persistance.Storage


class UnsubscribeHandlerTest {

    lateinit var testInstance: CryptoCore

    private lateinit var storageMock: Storage

    private val channelId = "channelId"
    private val BASE_LTC = "LTC"
    private val BASE_BCC = "BCC"
    private val TARGET_GBP = "GBP"
    private val TARGET_EUR = "EUR"
    private val EXCHANGE_GDAX = GdaxApi.NAME
    private val EXCHANGE_CRYPTONATOR = CryptonatorApi.NAME
    private val PERIODICITY = "every_5_minutes"

    @Before
    fun setup() {
        storageMock = StorageModule.storageMock
        whenever(storageMock.observeSubscriptionUpdates()).thenReturn(Observable.create<PreferenceUpdate> { })

        testInstance = CryptoCore.start(true)
    }

    @Test
    fun `no subscriptions to remove`() {
        //setup
        whenever(storageMock.getSubscriptions(channelId)).thenReturn(null)

        //action
        val response = testInstance.handle(channelId, "/unsubscribe")

        //assert
        assertThat(response.text).isEqualTo("You don't have subscriptions yet. To create new subscription use **/subscribe** command")
    }

    @Test
    fun `remove existing subscription successfully`() {
        //setup
        val stubSubscription = Subscription("uuid", "base", "target", "exchange", 5)
        whenever(storageMock.getSubscriptions(channelId)).thenReturn(listOf(stubSubscription))

        //action
        val response = testInstance.handle(channelId, "/unsubscribe")

        //assert
        assertThat(response.text).isEqualTo("Choose subscription to delete:")
    }

    @Test
    fun `test subscribe btc usd gdax`() {
        //action
        val reply = testInstance.handle(channelId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_CRYPTONATOR $PERIODICITY")

        //test
        assertThat(reply.text).isEqualTo("subscriptions created")
        verify(storageMock, times(1)).addSubscription(channelId, Subscription("uuid", BASE_LTC, TARGET_GBP, EXCHANGE_CRYPTONATOR, 5))
    }

    @Test
    fun `test multiple subscriptions`() {
        //action
        testInstance.handle(channelId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_GDAX $PERIODICITY")
        testInstance.handle(channelId, "/subscribe $BASE_BCC $TARGET_EUR $EXCHANGE_CRYPTONATOR $PERIODICITY")

        //test
        verify(storageMock, times(1)).addSubscription(channelId, Subscription("uuid", BASE_LTC, TARGET_GBP, EXCHANGE_GDAX, 5))
        verify(storageMock, times(1)).addSubscription(channelId, Subscription("uuid", BASE_BCC, TARGET_EUR, EXCHANGE_CRYPTONATOR, 5))
    }
}