package ru.justd.cryptobot.publisher

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.request.SendMessage
import io.reactivex.subjects.PublishSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.TelegramCryptAdviser
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.Subscription
import ru.justd.cryptobot.persistance.UserPreferences
import java.util.*

internal class PublisherImplTest {

    lateinit var testInstance: PublisherImpl

    private val botMock = mock<TelegramBot>()
    private val facadeMock = mock<ExchangeFacade>()
    private val storageMock = mock<Storage>()

    val requestCaptor = ArgumentCaptor.forClass(SendMessage::class.java)

    @Before
    fun setup() {}

    @Test
    fun `some test`() {
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
                        listOf(Subscription(base, target, exchange, 0))
                )
        ))

        Thread.sleep(100) //todo not cool

        //test
        verify(botMock).execute(requestCaptor.capture())
        assertThat(requestCaptor.value.parameters["text"]).isEqualTo(
                "*${TelegramCryptAdviser.INSTANCE_ID}*\n\n$base price is $amount $target"
        )

    }


}