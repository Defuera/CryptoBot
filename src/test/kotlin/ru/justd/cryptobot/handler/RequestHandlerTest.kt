package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class RequestHandlerTest {

    //    Help, Update, About, Price, UnsupportedRequest
    @Test
    fun testFindHelpRequestHandler() {
        assertThat(Request.handler("/help")).isEqualTo(Help)
    }

    @Test
    fun testFindUpdateRequestHandler() {
        assertThat(Request.handler("/update")).isEqualTo(Update)
    }

    @Test
    fun testFindAboutRequestHandler() {
        assertThat(Request.handler("/about")).isEqualTo(About)
    }

    @Test
    fun testFindPriceRequestHandler() {
        assertThat(Request.handler("/price")).isEqualTo(Unsupported)
        assertThat(Request.handler("/price Bitcoin")).isEqualTo(Unsupported)
        assertThat(Request.handler("/price 123")).isEqualTo(Unsupported)

        assertThat(Request.handler("/price hui")).isExactlyInstanceOf(Price::class.java) //todo add list of supported cryptos or determine it dynamically
        assertThat(Request.handler("/price BTC")).isExactlyInstanceOf(Price::class.java)
        assertThat(Request.handler("/price ETH")).isExactlyInstanceOf(Price::class.java)
    }


    //region integration tests

    @Test
    fun testGetPriceSuccess() {
        assertThat(Price.newInstance("/price BTC").responseMessage()).contains("BTC")
        assertThat(Price.newInstance("/price ETH").responseMessage()).contains("ETH")
    }

    @Test
    fun testGetPriceInvalidBaseCurrency() {
        assertThat(Price.newInstance("BCC").responseMessage()).isNotNull() //todo find better way to make sure expected error is returned, taking into account, that different apis return different errors. Do we need different errors?
    }

    //endregion

}