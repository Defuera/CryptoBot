package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported

@RunWith(MockitoJUnitRunner::class)
class PriceCommandHandlerTest {

    @Mock
    lateinit var exchangeFacade: ExchangeFacade

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetBtcPrice() {
        //setup
        val base = "BTC"
        `when`(exchangeFacade.getRate(base)).thenReturn(RateResponse(.0, base, "USD"))

        //test
        val message = PriceCommandHandler(exchangeFacade, base, null, null).responseMessage()

        //assert
        assertThat(message).matches(patternForPair(base, "USD"))
    }

    @Test
    fun testGetBtcPriceInEur() {
        //setup
        val base = "BTC"
        val target = "EUR"
        `when`(exchangeFacade.getRate(base, target)).thenReturn(RateResponse(.0, base, target))

        //test
        val message = PriceCommandHandler(exchangeFacade, base, target, null).responseMessage()

        //assert
        assertThat(message).matches(patternForPair(base, target))
    }

    private fun patternForPair(base: String, target: String) = Regex("$base price is [0-9.]+ $target").toPattern()


    @Test
    fun testFindPriceCommandHandlerInvalidExchange() {
        //setup
        val invalidExchange = "Invalid"
        val base = "BTC"
        val target = "USD"
        `when`(exchangeFacade.getRate(base, target, invalidExchange)).thenThrow(ExchangeNotSupported(invalidExchange))

        //action
        val message = PriceCommandHandler(exchangeFacade, base, target, invalidExchange).responseMessage()

        //assert
        assertThat(message).isEqualTo("$invalidExchange exchange not supported")
    }

}