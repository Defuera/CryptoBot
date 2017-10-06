package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class CommandHandlerTest {

    @Test
    fun testFindHelpCommandHandler() {
        assertThat(findHandler("/help")).isEqualTo(HelpCommandHandler)
    }

    @Test
    fun testFindUpdateCommandHandler() {
        assertThat(findHandler("/update")).isEqualTo(UpdateCommandHandler)
    }

    @Test
    fun testFindAboutCommandHandler() {
        assertThat(findHandler("/about")).isEqualTo(AboutCommandHandler)
    }

    @Test
    fun testFindPriceCommandHandlerFailed() {
        assertThat(findHandler("/price")).isEqualTo(UnsupportedCommandHandler)
        assertThat(findHandler("/price Bitcoin")).isEqualTo(UnsupportedCommandHandler)
        assertThat(findHandler("/price 123")).isEqualTo(UnsupportedCommandHandler)
    }

    @Test
    fun testFindPriceCommandHandlerSuccess() {
        assertThat(findFactory("/price hui")).isExactlyInstanceOf(PriceCommandHandlerFactory::class.java) //todo add list of supported cryptos or determine it dynamically
        assertThat(findFactory("/price BTC")).isExactlyInstanceOf(PriceCommandHandlerFactory::class.java)
        assertThat(findFactory("/price ETH")).isExactlyInstanceOf(PriceCommandHandlerFactory::class.java)
    }

    private fun findFactory(command: String) = Command.findCommandHandlerFactory(command)

    private fun findHandler(command: String) = findFactory(command).create()

}