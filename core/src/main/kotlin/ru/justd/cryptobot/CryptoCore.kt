package ru.justd.cryptobot

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.api.blockchain.bitcoin.BitcoinAddressValidator
import ru.justd.cryptobot.api.blockchain.ether.EtherAddressValidator
import ru.justd.cryptobot.api.blockchain.litecoin.LitecoinAddressValidator
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.di.DaggerCryptoCoreComponent
import ru.justd.cryptobot.di.MainModule
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.purchase.PurchaseHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.Update
import ru.justd.cryptobot.utils.ShiffrLogger
import javax.inject.Inject

class CryptoCore private constructor(
        clientName: String,
        val debug: Boolean,
        feedbackStorage: FeedbackStorage
) {

    companion object {
        fun start(clientName: String, debug: Boolean = true, feedbackStorage: FeedbackStorage) = CryptoCore(clientName, debug, feedbackStorage)
    }

    @Inject
    lateinit var publisher: Publisher

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    @Inject
    lateinit var analytics: Analytics

    @Inject
    lateinit var purchaseApi: PurchaseApi

    init {
        DaggerCryptoCoreComponent.builder()
                .mainModule(MainModule(debug))
                .storageModule(StorageModule(clientName, feedbackStorage))
                .build()
                .inject(this)
    }

    fun addCommandHandler(commandHandlerFactory: CommandHandlerFactory<*>) {
        commandHandlerFacade.addCommandHandler(commandHandlerFactory)
    }

    fun handle(channelId: String, isPrivate : Boolean, request: String): Reply {
        return commandHandlerFacade.handle(channelId, request, isPrivate)
    }

    fun setUpdateListener(listener: (update: Update) -> Unit) {
        publisher
                .updatesObservable()
                .subscribe { listener(it) }
    }


    //region payments todo payment should not be in core

    fun validateAddress(address: String, base: String): Boolean {
        if (debug) {
            return address.contains("1DiwvEJyvNxHCTDrzhJoqzkY7QrXGFuo26") //test address
        } else {
            return when (base.toUpperCase()) {
                "BTC" -> BitcoinAddressValidator.validateAddress(address)
                "ETH" -> EtherAddressValidator.validateAddress(address)
                "LTC" -> LitecoinAddressValidator.validateAddress(address)
                else -> throw IllegalArgumentException("cannot validate $base")
            }
        }
    }

    @Throws(TransferFailed::class)
    fun transferFunds(channelId: String, address: String, invoicePayload: PurchaseHandler.Payload): Reply {
        ShiffrLogger.log("tag", "$address, $invoicePayload")
        return purchaseApi.transferFunds(channelId, invoicePayload.base, invoicePayload.baseAmount, address)
    }

    //endregion

}