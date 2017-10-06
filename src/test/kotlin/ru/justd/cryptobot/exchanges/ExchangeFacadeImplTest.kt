package ru.justd.cryptobot.exchanges

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import ru.justd.cryptobot.UserPreferences
import ru.justd.cryptobot.exchanges.coinbase.CoinbaseApi
import ru.justd.cryptobot.exchanges.cryptonator.CryptonatorApi
import ru.justd.cryptobot.exchanges.gdax.GdaxApi

internal class ExchangeFacadeImplTest {

    lateinit var testInstance: ExchangeFacade

    @Mock
    lateinit var gdaxApi: ExchangeApi

    @Mock
    lateinit var coinbaseApi: ExchangeApi

    @Mock
    lateinit var cryptonatorApi: ExchangeApi

    @Mock
    lateinit var preferences: UserPreferences

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testInstance = ExchangeFacadeImpl(gdaxApi, coinbaseApi, cryptonatorApi, preferences)
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

}