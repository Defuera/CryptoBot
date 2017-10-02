package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import ru.justd.cryptobot.handler.Request.Companion.find

internal class RequestHandlerTest {

    //    Help, Update, About, Price, UnsupportedRequest
    @Test
    fun testFindHelpRequestHandler() {
        assertThat(Request.find("/help")).isEqualTo(Request.HELP)
    }

    @Test
    fun testFindUpdateRequestHandler() {
        assertThat(Request.find("/update")).isEqualTo(Request.UPDATE)
    }

    @Test
    fun testFindAboutRequestHandler() {
        assertThat(Request.find("/about")).isEqualTo(Request.ABOUT)
    }

    @Test
    fun testFindPriceRequestHandler() {
        assertThat(find("/price")).isEqualTo(Request.UNSUPPORTED)
        assertThat(find("/price Bitcoin")).isEqualTo(Request.UNSUPPORTED)
        assertThat(find("/price 123")).isEqualTo(Request.UNSUPPORTED)

        assertThat(find("/price hui")).isEqualTo(Request.PRICE) //todo add list of supported cryptos or determine it dynamically
        assertThat(find("/price BTC")).isEqualTo(Request.PRICE)
        assertThat(find("/price ETH")).isEqualTo(Request.PRICE)
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