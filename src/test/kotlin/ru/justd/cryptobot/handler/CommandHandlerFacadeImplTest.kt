package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported

internal class CommandHandlerFacadeImplTest {

    lateinit var testInstance: CommandHandlerFacade

    @Mock
    lateinit var exchangeFacade: ExchangeFacade

    @Before
    fun setup() {
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


    //region test find price handler with arguments

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBase() {
        val handler = testInstance.createCommandHandler("/price BTC") as PriceCommandHandler
        assertThat(handler.getBaseCode()).isEqualTo("BTC")
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBaseAnsTarget() {
        val handler = testInstance.createCommandHandler("/price BTC USD") as PriceCommandHandler
        assertThat(handler.getBaseCode()).isEqualTo("BTC")
        assertThat(handler.getTargetCode()).isEqualTo("USD")
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBaseAnsTargetAndExchange() {
        val handler = testInstance.createCommandHandler("/price BTC USD Coinbase") as PriceCommandHandler
        assertThat(handler.getBaseCode()).isEqualTo("BTC")
        assertThat(handler.getTargetCode()).isEqualTo("USD")
        assertThat(handler.getExchange()).isEqualTo("Coinbase")
    }

    @Test
    fun testFindPriceCommandHandlerInvalidExchange() {
        //setup
        val invalidExchange = "Invalid"
        `when`(exchangeFacade.getRate("BTC", "USD", invalidExchange)).thenThrow(ExchangeNotSupported(invalidExchange))

        //action
        val message = testInstance
                .createCommandHandler("/price BTC USD $invalidExchange")
                .responseMessage()

        //assert
        assertThat(message).isEqualTo("$invalidExchange exchange not supported")
    }

    //endregion

}