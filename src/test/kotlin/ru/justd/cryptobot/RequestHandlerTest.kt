package ru.justd.cryptobot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.justd.cryptobot.RequestHandler.Companion.findHandler
import ru.justd.cryptobot.RequestHandler.*

internal class RequestHandlerTest{

    @Test
    fun testFindPriceRequestHandler(){
        assertTrue(findHandler("/price") == UnsupportedRequest)
        assertTrue(findHandler("/price Bitcoin") == UnsupportedRequest)
        assertTrue(findHandler("/price 123") == UnsupportedRequest)

        assertTrue(findHandler("/price hui") == Price) //todo add list of supported cryptos or determine it dynamically
        assertTrue(findHandler("/price BTC") == Price)
        assertTrue(findHandler("/price ETH") == Price)
    }

}