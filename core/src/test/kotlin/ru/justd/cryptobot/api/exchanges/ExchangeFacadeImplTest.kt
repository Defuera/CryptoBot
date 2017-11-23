package ru.justd.cryptobot.api.exchanges

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.api.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.api.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.persistance.Storage

internal class ExchangeFacadeImplTest {

    lateinit var testInstance: ExchangeApiFacade

    @Mock
    lateinit var gdaxApi: ExchangeApi

    @Mock
    lateinit var coinbaseApi: ExchangeApi

    @Mock
    lateinit var cryptonatorApi: ExchangeApi

    @Mock
    lateinit var bitfinexApi: ExchangeApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testInstance = ExchangeApiFacadeImpl(gdaxApi, coinbaseApi, cryptonatorApi, bitfinexApi)
    }

    @Test
    fun testGdaxApiCalled() {
        val base = "BASE"
        val target = "TARGET"
        testInstance.getRate(base, target, GdaxApi.NAME)

        verify(gdaxApi, times(1)).getRate(base, target)
    }

    @Test
    fun testCoinbaseApiCalled() {
        val base = "BASE"
        val target = "TARGET"
        testInstance.getRate(base, target, CoinbaseApi.NAME)

        verify(coinbaseApi, times(1)).getRate(base, target)
    }

    @Test
    fun testCryptonatorApiCalled() {
        val base = "BASE"
        val target = "TARGET"
        testInstance.getRate(base, target, CryptonatorApi.NAME)

        verify(cryptonatorApi, times(1)).getRate(base, target)
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