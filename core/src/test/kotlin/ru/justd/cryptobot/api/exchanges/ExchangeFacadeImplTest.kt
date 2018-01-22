package ru.justd.cryptobot.api.exchanges

import okhttp3.OkHttpClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported

internal class ExchangeFacadeImplTest {

    lateinit var testInstance: ExchangeApiFacade

    @Mock
    lateinit var okhttpClient: OkHttpClient

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testInstance = ExchangeApiFacadeImpl(okhttpClient)
    }

    @Test
    fun testInvalidExchange() {
        val base = "BASE"
        val target = "TARGET"
        try {
            testInstance.getRate(base, target, "Invalid")
        } catch (exception: Exception) {
            assertThat(exception).isExactlyInstanceOf(ExchangeNotSupported::class.java)
        }

    }

}