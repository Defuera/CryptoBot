package ru.justd.cryptobot.handler

import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.handler.price.PriceCommandHandler

@RunWith(MockitoJUnitRunner::class)
class PriceCommandHandlerTest {

    @Mock
    lateinit var exchangeFacade: ExchangeApiFacade

    @Mock
    lateinit var analytics: Analytics

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test get btc-usd pair from gdax`() {
        //setup
        val base = "btc"
        val target = "usd"
        val exchange = "gdax"
        whenever(exchangeFacade.getRate(base, target, exchange)).thenReturn(RateResponse(.0, base, target))

        //test
        val message = PriceCommandHandler(analytics, exchangeFacade, base, target, exchange).createReply("channelId")

        //assert
        assertThat(message.text).isEqualTo("${base.toUpperCase()} price is 0.0 ${target.toUpperCase()} (via $exchange)")
    }

    @Test
    fun testFindPriceCommandHandlerInvalidExchange() {
        //setup
        val invalidExchange = "Invalid"
        val base = "BTC"
        val target = "USD"
        whenever(exchangeFacade.getRate(base, target, invalidExchange)).thenThrow(ExchangeNotSupported(invalidExchange))

        //action
        val message = PriceCommandHandler(analytics, exchangeFacade, base, target, invalidExchange).createReply("channelId")

        //assert
        assertThat(message.text).isEqualTo("$invalidExchange exchange not supported")
    }

}