package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.handler.subscribe.SubscribeHandler
import ru.justd.cryptobot.persistance.Storage

internal class CommandHandlerFacadeImplTest {

    lateinit var testInstance: CommandHandlerFacade

    @Mock
    lateinit var exchangeFacade: ExchangeFacade

    @Mock
    lateinit var storage: Storage

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testInstance = CommandHandlerFacadeImpl(exchangeFacade, storage)
    }


    @Test
    fun testFindHelpCommandHandler() {
        assertThat(testInstance.createCommandHandler("userId", "/help")).isEqualTo(HelpCommandHandler)
    }

    @Test
    fun testFindUpdateCommandHandler() {
        assertThat(testInstance.createCommandHandler("userId",  "/update")).isEqualTo(UpdateCommandHandler)
    }

    @Test
    fun testFindAboutCommandHandler() {
        assertThat(testInstance.createCommandHandler("userId",  "/about")).isEqualTo(AboutCommandHandler)
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBase() {
        assertThat(testInstance.createCommandHandler("userId",  "/price BTC")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBaseAndTarget() {
        assertThat(testInstance.createCommandHandler("userId",  "/price BTC")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBaseAndTargetAndExchange() {
        val commandHandler = testInstance.createCommandHandler("userId", "/price BTC USD Coinbase")
        assertThat(commandHandler).isExactlyInstanceOf(PriceCommandHandler::class.java)

        val priceHandler = commandHandler as PriceCommandHandler
        assertThat(priceHandler.base).isEqualTo("BTC")
        assertThat(priceHandler.target).isEqualTo("USD")
        assertThat(priceHandler.exchange).isEqualTo("Coinbase")
    }

    @Test
    fun `test find subscribe handler providing base`() {
        val commandHandler = testInstance.createCommandHandler("userId", "/subscribe BTC USD Coinbase")
        assertThat(commandHandler).isExactlyInstanceOf(SubscribeHandler::class.java)

        val priceHandler = commandHandler as SubscribeHandler
        assertThat(priceHandler.base).isEqualTo("BTC")
        assertThat(priceHandler.target).isEqualTo("USD")
        assertThat(priceHandler.exchange).isEqualTo("Coinbase")
    }


}