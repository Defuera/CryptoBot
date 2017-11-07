package ru.justd.cryptobot.handler

import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import ru.justd.cryptobot.CryptoCore
import ru.justd.cryptobot.api.blockchain.bitcoin.BitcoinAddressInfo
import ru.justd.cryptobot.di.BlockchainModule
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.persistance.PreferenceUpdate


internal class WalletInfoHandlerTest {

    val bitcoinInfoApiMock = BlockchainModule.blockchainInfoApiFacade
    lateinit var testInstance: CryptoCore

    @Before
    fun setup() {
        whenever(StorageModule.storageMock.observeUpdates()).thenReturn(Observable.create<PreferenceUpdate> { })
        testInstance = CryptoCore()
    }


    @Test
    fun testSuccess() {
        //setup
        whenever(bitcoinInfoApiMock.getAddressInfo(anyString())).thenReturn(
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