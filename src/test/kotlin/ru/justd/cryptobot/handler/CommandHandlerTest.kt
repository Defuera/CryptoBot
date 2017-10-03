package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class CommandHandlerTest {

    @Test
    fun testFindHelpCommandHandler() {
        assertThat(Command.findCommandHandler("/help")).isEqualTo(HelpCommandHandler)
    }

    @Test
    fun testFindUpdateCommandHandler() {
        assertThat(Command.findCommandHandler("/update")).isEqualTo(UpdateCommandHandler)
    }

    @Test
    fun testFindAboutCommandHandler() {
        assertThat(Command.findCommandHandler("/about")).isEqualTo(AboutCommandHandler)
    }

    @Test
    fun testFindPriceCommandHandler() {
        assertThat(Command.findCommandHandler("/price")).isEqualTo(UnsupportedCommandHandler)
        assertThat(Command.findCommandHandler("/price Bitcoin")).isEqualTo(UnsupportedCommandHandler)
        assertThat(Command.findCommandHandler("/price 123")).isEqualTo(UnsupportedCommandHandler)

        assertThat(Command.findCommandHandler("/price hui")).isExactlyInstanceOf(PriceCommandHandler::class.java) //todo add list of supported cryptos or determine it dynamically
        assertThat(Command.findCommandHandler("/price BTC")).isExactlyInstanceOf(PriceCommandHandler::class.java)
        assertThat(Command.findCommandHandler("/price ETH")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }

}