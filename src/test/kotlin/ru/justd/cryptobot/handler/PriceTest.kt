package ru.justd.cryptobot.handler

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


internal class PriceTest {

    @Test
    fun testGetBtcPrice() {
        assertThat(priceFor("BTC")).matches(patternForPair("BTC", "USD"))
        assertThat(priceFor("ETH")).matches(patternForPair("ETH", "USD"))
    }

    @Test
    fun testGetEthPrice() {
        assertThat(priceFor("BTC")).matches(patternForPair("BTC", "USD"))
        assertThat(priceFor("ETH")).matches(patternForPair("ETH", "USD"))
    }

    @Test
    fun testGetPriceWithCuptomFiatSuccess() {
        assertThat(PriceCommandHandler.newInstance("/price BTC-EUR").responseMessage()).matches(patternForPair("BTC", "EUR"))
    }

    private fun priceFor(base: String) = PriceCommandHandler.newInstance("/price $base").responseMessage()

    private fun patternForPair(base: String, target: String) = Regex("$base price is [0-9.]+ $target").toPattern()

    @Test
    fun testGetPriceInvalidBaseCurrency() {
        assertThat(PriceCommandHandler.newInstance("BCC").responseMessage()).isNotNull() //todo find better way to make sure expected error is returned, taking into account, that different apis return different errors. Do we need different errors?
    }

}