package ru.justd.cryptobot.api.blockchain.ether

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class EtherAddressValidatorTest {

    @Test
    fun `test ethereum address validator`() {
        assertThat(EtherAddressValidator.validateAddress("0x0F6bd6CDb7b8a1D2Da9Cb6b03c59Bb082f833a58")).isTrue()
        assertThat(EtherAddressValidator.validateAddress("0x0F6bd6C6b03c59Bb082f833a58")).isFalse()
        assertThat(EtherAddressValidator.validateAddress("1x0F6bd6CDb7b8a1D2Da9Cb6b03c59Bb082f833a58")).isFalse()
        assertThat(EtherAddressValidator.validateAddress("1Ef4R8qB3YSMrfojH64rTFx4Vg1NXgVg6d")).isFalse()
    }

}