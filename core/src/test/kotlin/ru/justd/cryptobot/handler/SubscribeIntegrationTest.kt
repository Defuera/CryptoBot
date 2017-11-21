package ru.justd.cryptobot.handler

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
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
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.di.UtilsModule.Companion.timeManagerMock
import ru.justd.cryptobot.di.UtilsModule.Companion.uuidGeneratorMock
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.persistance.Storage
import utils.TimeManagerImpl.PERIOD_12_HOURS

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
        whenever(timeManagerMock.getUpdatesPeriod()).thenReturn(1000L) //if you return 0L then update will be called more then once which leads to test failure
        whenever(timeManagerMock.createPublishTimes(anyLong(), anyString())).thenReturn(listOf("12:00"))
        whenever(uuidGeneratorMock.random()).thenReturn("uuid")

        testInstance = CryptoCore.start(true)
    }

    @Test
    fun `test command without arguments returns returns cryptos list`() {
        val response = testInstance.handle(channelId, "/subscribe")

        assertThat(response.channelId).isEqualTo(channelId)
        assertThat(response.text).isEqualTo("Choose crypto")

        val dialog = response.dialog!!
        assertThat(dialog.callbackLabel).isEqualTo("/subscribe")
        checkOptions(dialog.dialogOptions, "BTC", "ETH", "BCC")
    }

    /**
     * Assert given dialog options following options
     */
    private fun checkOptions(dialogOptions: List<Option>, vararg options: String) {
        assertThat(dialogOptions.map { it.name }).isEqualTo(options.toList())
    }

    @Test
    fun `test subscribe btc usd gdax with 12 hours period`() {
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
                        BASE_LTC,
                        TARGET_GBP,
                        EXCHANGE_CRYPTONATOR,
                        listOf("12:00")
                )
        )
    }

}