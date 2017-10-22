package ru.justd.cryptobot.publisher

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.subjects.PublishSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.messenger.Messenger
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.UserPreferences
import java.util.*

internal class PublisherImplTest {

    lateinit var testInstance: PublisherImpl

    private val botMock = mock<Messenger>()
    private val facadeMock = mock<ExchangeFacade>()
    private val storageMock = mock<Storage>()

    @Test
    fun `verify update being published after storage new subscription added to storage`() {
        //setup
        val subject = PublishSubject.create<Update>()
        whenever(storageMock.observeUpdates()).thenReturn(subject)
        val base = "btc"
        val target = "usd"
        val channelId = "100"
        val exchange = "gdax"
        val amount = 0.2

        val rateResponse = RateResponse(amount, base, target)
        whenever(facadeMock.getRate(anyString(), anyString(), anyString())).thenReturn(rateResponse)

        testInstance = PublisherImpl(botMock, facadeMock, storageMock)

        //action
        subject.onNext(Update(
                channelId,
                UserPreferences(
                        "base",
                        "target",
                        "exchange",
                        Locale.CANADA,
                        listOf(Subscription(base, target, exchange, 200))
                )
        ))

        Thread.sleep(100) //todo not cool, but otherwise it's not working, since publisher working in different thread

        //test
        argumentCaptor<String>().apply {
            verify(botMock).sendMessage(anyString(), capture())
            assertThat(firstValue).isEqualTo("$base price is $amount $target")
        }

    }

}