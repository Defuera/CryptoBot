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


    //region integration tests //todo move from this file

    @Test
    fun testGetPriceSuccess() {
        assertThat(priceFor("BTC")).matches(patternForPair("BTC", "USD"))
        assertThat(priceFor("ETH")).matches(patternForPair("ETH", "USD"))
    }

    @Test
    fun testGetPriceWithCuptomFiatSuccess() {
        assertThat(Price.newInstance("/price BTC-EUR").responseMessage()).matches(patternForPair("BTC", "EUR"))
        assertThat(priceFor("ETH")).matches(patternForPair("ETH", "USD"))
    }

    private fun priceFor(base: String) = Price.newInstance("/price $base").responseMessage()

    private fun patternForPair(base: String, target: String) = Regex("$base price is [0-9.]+ $target").toPattern()

    @Test
    fun testGetPriceInvalidBaseCurrency() {
        assertThat(Price.newInstance("BCC").responseMessage()).isNotNull() //todo find better way to make sure expected error is returned, taking into account, that different apis return different errors. Do we need different errors?
    }

    //endregion

}