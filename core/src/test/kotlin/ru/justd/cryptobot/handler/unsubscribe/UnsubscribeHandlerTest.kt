package ru.justd.cryptobot.handler.unsubscribe

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.persistance.Storage


class UnsubscribeHandlerTest {

    lateinit var testInstance: CryptoCore

    private lateinit var storageMock: Storage

    private val channelId = "channelId"

    @Before
    fun setup() {
        storageMock = StorageModule.storageMock
        testInstance = CryptoCore.start("", true, mock())
    }

    @Test
    fun `no subscriptions to remove`() {
        //setup
        whenever(storageMock.getSubscriptions(channelId)).thenReturn(null)

        //action
        val response = testInstance.handle(Inquiry(channelId, false, "/unsubscribe"))

        //assert
        assertThat(response.text).isEqualTo("You don't have subscriptions yet. To create new subscription use **/subscribe** command")
    }

    @Test
    fun `remove existing subscription successfully`() {
        //setup
        val stubSubscription = Subscription("uuid", "channelId", "fiatCurrency", "exchange", 5, "cryptoAsset")
        whenever(storageMock.getSubscriptions(anyString())).thenReturn(listOf(stubSubscription))

        //action
        val response = testInstance.handle(Inquiry(channelId, false, "/unsubscribe"))

        //assert
        assertThat(response.text).isEqualTo("Choose subscription to delete:")
    }

}