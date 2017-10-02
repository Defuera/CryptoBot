package ru.justd.cryptobot

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.justd.cryptobot.RequestHandler.Companion.findHandler
import ru.justd.cryptobot.RequestHandler.*

internal class RequestHandlerTest {

    //    Help, Update, About, Price, UnsupportedRequest
    @Test
    fun testFindHelpRequestHandler() {
        assertThat(RequestHandler.findHandler("/help")).isEqualTo(Help)
    }

    @Test
    fun testFindUpdateRequestHandler() {
        assertThat(RequestHandler.findHandler("/update")).isEqualTo(Update)
    }

    @Test
    fun testFindAboutRequestHandler() {
        assertThat(RequestHandler.findHandler("/about")).isEqualTo(About)
    }

    @Test
    fun testFindPriceRequestHandler() {
        assertThat(findHandler("/price")).isEqualTo(UnsupportedRequest)
        assertThat(findHandler("/price Bitcoin")).isEqualTo(UnsupportedRequest)
        assertThat(findHandler("/price 123")).isEqualTo(UnsupportedRequest)

        assertThat(findHandler("/price hui")).isExactlyInstanceOf(Price::class.java) //todo add list of supported cryptos or determine it dynamically
        assertThat(findHandler("/price BTC")).isExactlyInstanceOf(Price::class.java)
        assertThat(findHandler("/price ETH")).isExactlyInstanceOf(Price::class.java)
    }


    //region integration tests

    @Test
    fun testGetPriceSuccess() {
        assertThat(Price.newInstance("/price BTC").responseMessage()).contains("BTC")
        assertThat(Price.newInstance("/price ETH").responseMessage()).contains("ETH")
    }

    @Test
    fun testGetPriceInvalidBaseCurrency() {
        assertThat(Price.newInstance("BCC").responseMessage()).isEqualToIgnoringCase("Invalid base currency") //todo this is only for coinbase
    }

    //endregion

}