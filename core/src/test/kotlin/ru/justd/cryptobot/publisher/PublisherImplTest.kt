package ru.justd.cryptobot.publisher

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.observers.TestObserver
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.persistance.PreferenceUpdate
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.UserPreferences
import java.util.*

internal class PublisherImplTest {

    lateinit var testInstance: PublisherImpl

    private val facadeMock = mock<ExchangeApiFacade>()
    private val storageMock = mock<Storage>()

    @Test
    fun `verify update being published after storage new subscription added to storage`() {
        //setup
        val subject = PublishSubject.create<PreferenceUpdate>()
        whenever(storageMock.observeSubscriptionUpdates()).thenReturn(subject)
        val base = "btc"
        val target = "usd"
        val channelId = "100"
        val exchange = "gdax"
        val amount = 0.2

        val rateResponse = RateResponse(amount, base, target)
        whenever(facadeMock.getRate(anyString(), anyString(), anyString())).thenReturn(rateResponse)

        testInstance = PublisherImpl(facadeMock, storageMock)

        val testObserver = TestObserver.create<Update>()
        testInstance.observeUpdates().subscribe(testObserver)

        //action
        subject.onNext(PreferenceUpdate(
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
        testObserver.assertValue(Update(channelId, "$base price is $amount $target"))

    }

}