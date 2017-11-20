package ru.justd.cryptobot.handler

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.di.ExchangeApiModule
import ru.justd.cryptobot.di.MainModule
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.handler.subscribe.SubscribeHandler.Companion.PERIOD_12_HOURS
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.persistance.PreferenceUpdate
import ru.justd.cryptobot.persistance.Storage

internal class SubscribeIntegrationTest {

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
        whenever(MainModule.dateManagerMock.periodToDateTimesList(anyLong(), anyLong())).thenReturn(listOf("12:00"))

        testInstance = CryptoCore.start(true)
    }

    @Test
    fun `test base is absent throws exception`() {
        val response = testInstance.handle(channelId, "/subscribe")

        assertThat(response.channelId).isEqualTo(channelId)
        assertThat(response.text).isEqualTo("Choose crypto")

        val dialog = response.dialog!!
        assertThat(dialog.callbackLabel).isEqualTo("/subscribe")
        assertThat(dialog.dialogOptions).isEqualTo(arrayOf("BTC", "ETH", "BCC"))
    }

    @Test
    fun `test subscribe btc usd gdax`() {
        //setup
        whenever(ExchangeApiModule.exchangeFacade.getRate(anyString(), anyString(), anyString()))
                .thenReturn(RateResponse(
                        300.0, BASE_LTC, TARGET_GBP
                ))

        //action
        val reply = testInstance.handle(channelId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_CRYPTONATOR $PERIOD_12_HOURS")

        //test
        assertThat(reply.text).isEqualTo("Subscription created successfully!\nLTC price is 300.0 GBP (via CRYPTONATOR)")
        verify(storageMock, times(1)).addSubscription(
                channelId,
                Subscription(
                        "uuid",
                        "channelId",
                        TARGET_GBP,
                        EXCHANGE_CRYPTONATOR,
                        listOf("12:00"),
                        BASE_LTC
                )
        )
    }

    @Test
    fun `test multiple subscriptions`() {
        //action
        testInstance.handle(channelId, "/subscribe $BASE_LTC $TARGET_GBP $EXCHANGE_GDAX $PERIODICITY")
        testInstance.handle(channelId, "/subscribe $BASE_BCC $TARGET_EUR $EXCHANGE_CRYPTONATOR $PERIODICITY")

        //test
        verify(storageMock, times(1)).addSubscription(channelId, Subscription("uuid", "channelId", TARGET_GBP, EXCHANGE_GDAX, 5, BASE_LTC))
        verify(storageMock, times(1)).addSubscription(channelId, Subscription("uuid", "channelId", TARGET_EUR, EXCHANGE_CRYPTONATOR, 5, BASE_BCC))
    }
}