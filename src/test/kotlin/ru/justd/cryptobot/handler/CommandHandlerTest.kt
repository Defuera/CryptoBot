package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.exchanges.ExchangeFacade

internal class CommandHandlerTest {

    @Mock
    lateinit var exchangeFacade : ExchangeFacade //todo we don't actually need it here. remove

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    fun testFindHelpCommandHandler() {
        assertThat(Command.findCommandHandler(exchangeFacade,"/help")).isEqualTo(HelpCommandHandler)
    }

    @Test
    fun testFindUpdateCommandHandler() {
        assertThat(Command.findCommandHandler(exchangeFacade, "/update")).isEqualTo(UpdateCommandHandler)
    }

    @Test
    fun testFindAboutCommandHandler() {
        assertThat(Command.findCommandHandler(exchangeFacade, "/about")).isEqualTo(AboutCommandHandler)
    }

    @Test
    fun testFindPriceCommandHandler() {
        assertThat(Command.findCommandHandler(exchangeFacade, "/price")).isEqualTo(UnsupportedCommandHandler)
        assertThat(Command.findCommandHandler(exchangeFacade, "/price Bitcoin")).isEqualTo(UnsupportedCommandHandler)
        assertThat(Command.findCommandHandler(exchangeFacade, "/price 123")).isEqualTo(UnsupportedCommandHandler)

        assertThat(Command.findCommandHandler(exchangeFacade, "/price hui")).isExactlyInstanceOf(PriceCommandHandler::class.java) //todo add list of supported cryptos or determine it dynamically
        assertThat(Command.findCommandHandler(exchangeFacade, "/price BTC")).isExactlyInstanceOf(PriceCommandHandler::class.java)
        assertThat(Command.findCommandHandler(exchangeFacade, "/price ETH")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }

}