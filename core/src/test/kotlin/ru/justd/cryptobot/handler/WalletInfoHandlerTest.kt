package ru.justd.cryptobot.handler

import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.api.blockchain.bitcoin.BitcoinAddressInfo
import ru.justd.cryptobot.di.BlockchainModule


internal class WalletInfoHandlerTest {

    val blockchainApi = BlockchainModule.blockchainApi
    lateinit var testInstance: CryptoCore

    @Before
    fun setup() {
        testInstance = CryptoCore.start(true)
    }


    @Test
    fun testSuccess() {
        //setup
        whenever(blockchainApi.getAddressInfo(anyString())).thenReturn(
                BitcoinAddressInfo("6917027", "", "", "", "", "",
                        "", "", "", "", "", "",
                        "")
        )

        //action
        val reply = testInstance.handle("chatId", "/info 1EuxvSVf5yWLYtHiDkzbcd7pp5cooqPfJD")

        //test
        assertThat(reply.text).isEqualTo("You have 0.06917027 BTC")
    }

}