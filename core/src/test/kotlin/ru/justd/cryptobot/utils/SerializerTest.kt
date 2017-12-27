package ru.justd.cryptobot.utils

import org.assertj.core.api.Assertions
import org.junit.Test
import ru.justd.cryptobot.handler.purchase.PurchaseHandler

internal class SerializerTest {

    @Test
    fun `test serialize deserialize`() {
        val testInstance = PurchaseHandler.Payload("BTC", 0.05, "EUR", 100)
        val deserialisedInstance = Serializer.deserialize(Serializer.serialize(testInstance))
        Assertions.assertThat(deserialisedInstance).isEqualTo(testInstance)
    }

}