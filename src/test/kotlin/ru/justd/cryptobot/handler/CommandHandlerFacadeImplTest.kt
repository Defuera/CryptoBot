package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.exchanges.ExchangeFacade

internal class CommandHandlerFacadeImplTest {

    lateinit var testInstance : CommandHandlerFacade

    @Mock
    lateinit var exchangeFacade: ExchangeFacade

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        testInstance = CommandHandlerFacadeImpl(exchangeFacade)
    }


    @Test
    fun testFindHelpCommandHandler() {
        assertThat(testInstance.createCommandHandler("/help")).isEqualTo(HelpCommandHandler)
    }

    @Test
    fun testFindUpdateCommandHandler() {
        assertThat(testInstance.createCommandHandler("/update")).isEqualTo(UpdateCommandHandler)
    }

    @Test
    fun testFindAboutCommandHandler() {
        assertThat(testInstance.createCommandHandler("/about")).isEqualTo(AboutCommandHandler)
    }

    @Test
    fun testFindPriceCommandHandlerFailed() {
        assertThat(testInstance.createCommandHandler("/price")).isEqualTo(UnsupportedCommandHandler)
        assertThat(testInstance.createCommandHandler("/price Bitcoin")).isEqualTo(UnsupportedCommandHandler)
        assertThat(testInstance.createCommandHandler("/price 123")).isEqualTo(UnsupportedCommandHandler)
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBase() {
        val handler = testInstance.createCommandHandler("/price BTC") as PriceCommandHandler
        assertThat(handler.getCurrencyCode()).isEqualTo("BTC")
    }

}