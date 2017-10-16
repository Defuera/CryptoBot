package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.exchanges.ExchangeFacade
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
        assertThat(testInstance.createCommandHandler("userId",  "/price BTC Coinbase")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }


}