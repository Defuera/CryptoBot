package ru.justd.cryptobot.publisher

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.persistance.Storage
import utils.TimeManager

internal class PublisherImplTest {

    lateinit var testInstance: PublisherImpl

    private val facadeMock = mock<ExchangeApiFacade>()
    private val storageMock = mock<Storage>()
    private val timeManagerMock = mock<TimeManager>()

    private val SUBSCRIPTION_1 = Subscription("uuid", "chennelId", "btK", "usde", "gdax", listOf("22:55"))

    @Test
    fun `test updates published periodically`() {
        //setup
        whenever(storageMock.getAllSubscriptions()).thenReturn(listOf(SUBSCRIPTION_1))
        whenever(timeManagerMock.getUpdatesPeriod()).thenReturn(100L)
        whenever(timeManagerMock.isTimeToPublish(SUBSCRIPTION_1)).thenReturn(true)
        whenever(facadeMock.getRate(anyString(), anyString(), anyString())).thenReturn(RateResponse(0.23, "btK", "usde"))

        testInstance = PublisherImpl(facadeMock, storageMock, timeManagerMock)

        val testObserver = TestObserver.create<Update>()
        testInstance.updatesObservable().subscribe(testObserver)

        Thread.sleep(50)

        testObserver.assertValue(Update("chennelId", "BTK price is 0.23 USDE (via gdax)"))
    }

}