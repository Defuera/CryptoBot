package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class CommandHandlerTest {

    //    HelpCommandHandler, UpdateCommandHandler, AboutCommandHandler, PriceCommandHandler, UnsupportedRequest
    @Test
    fun testFindHelpRequestHandler() {
        assertThat(Command.findCommandHandler("/help")).isEqualTo(HelpCommandHandler)
    }

    @Test
    fun testFindUpdateRequestHandler() {
        assertThat(Command.findCommandHandler("/update")).isEqualTo(UpdateCommandHandler)
    }

    @Test
    fun testFindAboutRequestHandler() {
        assertThat(Command.findCommandHandler("/about")).isEqualTo(AboutCommandHandler)
    }

    @Test
    fun testFindPriceRequestHandler() {
        assertThat(Command.findCommandHandler("/price")).isEqualTo(UnsupportedCommandHandler)
        assertThat(Command.findCommandHandler("/price Bitcoin")).isEqualTo(UnsupportedCommandHandler)
        assertThat(Command.findCommandHandler("/price 123")).isEqualTo(UnsupportedCommandHandler)

        assertThat(Command.findCommandHandler("/price hui")).isExactlyInstanceOf(PriceCommandHandler::class.java) //todo add list of supported cryptos or determine it dynamically
        assertThat(Command.findCommandHandler("/price BTC")).isExactlyInstanceOf(PriceCommandHandler::class.java)
        assertThat(Command.findCommandHandler("/price ETH")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }


    //region integration tests

    @Test
    fun testGetPriceSuccess() {
        assertThat(PriceCommandHandler.newInstance("/price BTC").responseMessage()).contains("BTC")
        assertThat(PriceCommandHandler.newInstance("/price ETH").responseMessage()).contains("ETH")
    }

    @Test
    fun testGetPriceInvalidBaseCurrency() {
        assertThat(PriceCommandHandler.newInstance("BCC").responseMessage()).isNotNull() //todo find better way to make sure expected error is returned, taking into account, that different apis return different errors. Do we need different errors?
    }

    //endregion

}