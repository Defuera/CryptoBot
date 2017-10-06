package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse

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
        `when`(exchangeFacade.getRate(anyString())).thenReturn(RateResponse(.0, "BTC", "USD"))
        assertThat(priceFor("BTC")).matches(patternForPair("BTC", "USD"))
    }

    @Test
    fun testGetEthPrice() { //todo test is failing
        `when`(exchangeFacade.getRate("ETH")).thenReturn(RateResponse(.0, "ETH", "USD"))
        assertThat(priceFor("ETH")).matches(patternForPair("ETH", "USD"))
    }

//    @Test
//    fun testGetPriceWithCustomFiatSuccess() {
//        `when`(exchangeFacade.getRate("BTC", "EUR")).thenReturn(RateResponse(.0, "BTC", "EUR"))
//        assertThat(PriceCommandHandler.newInstance("/price BTC-EUR").responseMessage()).matches(patternForPair("BTC", "EUR"))
//    }

    private fun priceFor(base: String): String {
        val priceHandler = PriceCommandHandler(exchangeFacade, "/price $base")
        return priceHandler.responseMessage()
    }

    private fun patternForPair(base: String, target: String) = Regex("$base price is [0-9.]+ $target").toPattern()

//    @Test
//    fun testGetPriceInvalidBaseCurrency() {
//        assertThat(PriceCommandHandler.newInstance("BCC").responseMessage()).isNotNull() //todo find better way to make sure expected error is returned, taking into account, that different apis return different errors. Do we need different errors?
//    }

}