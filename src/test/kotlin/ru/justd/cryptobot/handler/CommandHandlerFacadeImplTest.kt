package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.persistance.UserPreferences
import ru.justd.cryptobot.exchanges.ExchangeFacade

internal class CommandHandlerFacadeImplTest {

    lateinit var testInstance: CommandHandlerFacade

    @Mock
    lateinit var exchangeFacade: ExchangeFacade

    @Mock
    lateinit var userPreferences: UserPreferences

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testInstance = CommandHandlerFacadeImpl(exchangeFacade, userPreferences)
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
    fun testFindPriceCommandHandlerWithSpecifiedBase() {
        assertThat(testInstance.createCommandHandler("/price BTC")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBaseAndTarget() {
        assertThat(testInstance.createCommandHandler("/price BTC")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }

    @Test
    fun testFindPriceCommandHandlerWithSpecifiedBaseAndTargetAndExchange() {
        assertThat(testInstance.createCommandHandler("/price BTC Coinbase")).isExactlyInstanceOf(PriceCommandHandler::class.java)
    }


}