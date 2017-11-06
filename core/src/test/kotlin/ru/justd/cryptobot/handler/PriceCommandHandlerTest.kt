package ru.justd.cryptobot.handler

import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import ru.justd.cryptobot.api.exchanges.ExchangeFacade
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.handler.price.PriceCommandHandler

@RunWith(MockitoJUnitRunner::class)
class PriceCommandHandlerTest {

    companion object {

        private const val FLOAT_REGEX = "[+-]?([0-9]*[.])?[0-9]+"

    }

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
        whenever(exchangeFacade.getRate(base)).thenReturn(RateResponse(.0, base, "USD"))

        //test
        val message = PriceCommandHandler(exchangeFacade, base, null, null).createReply("channelId")

        //assert
        assertThat(message.text).matches(patternForPair(base, "USD"))
    }

    @Test
    fun testGetBtcPriceInEur() {
        //setup
        val base = "BTC"
        val target = "EUR"
        whenever(exchangeFacade.getRate(base, target)).thenReturn(RateResponse(.0, base, target))

        //test
        val message = PriceCommandHandler(exchangeFacade, base, target, null).createReply("channelId")

        //assert
        assertThat(message.text).matches(patternForPair(base, target))
    }

    private fun patternForPair(base: String, target: String) = Regex("$base price is $FLOAT_REGEX $target").toPattern()


    @Test
    fun testFindPriceCommandHandlerInvalidExchange() {
        //setup
        val invalidExchange = "Invalid"
        val base = "BTC"
        val target = "USD"
        whenever(exchangeFacade.getRate(base, target, invalidExchange)).thenThrow(ExchangeNotSupported(invalidExchange))

        //action
        val message = PriceCommandHandler(exchangeFacade, base, target, invalidExchange).createReply("channelId")

        //assert
        assertThat(message.text).isEqualTo("$invalidExchange exchange not supported")
    }

}