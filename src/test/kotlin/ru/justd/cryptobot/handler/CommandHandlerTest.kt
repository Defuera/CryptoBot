package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class CommandHandlerTest {

    //    Help, Update, About, Price, UnsupportedRequest
    @Test
    fun testFindHelpRequestHandler() {
        assertThat(Command.findCommandHandler("/description")).isEqualTo(Help)
    }

    @Test
    fun testFindUpdateRequestHandler() {
        assertThat(Command.findCommandHandler("/update")).isEqualTo(Update)
    }

    @Test
    fun testFindAboutRequestHandler() {
        assertThat(Command.findCommandHandler("/about")).isEqualTo(About)
    }

    @Test
    fun testFindPriceRequestHandler() {
        assertThat(Command.findCommandHandler("/price")).isEqualTo(Unsupported)
        assertThat(Command.findCommandHandler("/price Bitcoin")).isEqualTo(Unsupported)
        assertThat(Command.findCommandHandler("/price 123")).isEqualTo(Unsupported)

        assertThat(Command.findCommandHandler("/price hui")).isExactlyInstanceOf(Price::class.java) //todo add list of supported cryptos or determine it dynamically
        assertThat(Command.findCommandHandler("/price BTC")).isExactlyInstanceOf(Price::class.java)
        assertThat(Command.findCommandHandler("/price ETH")).isExactlyInstanceOf(Price::class.java)
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