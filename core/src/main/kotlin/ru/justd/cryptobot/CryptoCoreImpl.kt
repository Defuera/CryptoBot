package ru.justd.cryptobot

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.di.DaggerCryptoCoreComponent
import ru.justd.cryptobot.di.MainModule
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.handler.purchase.PurchaseFacade
import ru.justd.cryptobot.handler.purchase.PurchaseHandler
import ru.justd.cryptobot.messenger.model.Inquiry
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.Update
import ru.justd.cryptobot.utils.LoggerConfig
import javax.inject.Inject

class CryptoCoreImpl private constructor(
        clientName: String,
        debug: Boolean,
        feedbackStorage: FeedbackStorage
) : CryptoCore {

    companion object {
        fun start(clientName: String, debug: Boolean = true, feedbackStorage: FeedbackStorage) = CryptoCoreImpl(clientName, debug, feedbackStorage)
    }

    @Inject
    lateinit var publisher: Publisher

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    @Inject
    lateinit var analytics: Analytics

    @Inject
    lateinit var purchaseFacade : PurchaseFacade

    init {
        DaggerCryptoCoreComponent.builder()
                .mainModule(MainModule(debug))
                .storageModule(StorageModule(clientName, feedbackStorage))
                .build()
                .inject(this)

        LoggerConfig.debug = debug
    }

    override fun addCommandHandler(commandHandlerFactory: CommandHandlerFactory<*>) {
        commandHandlerFacade.addCommandHandler(commandHandlerFactory)
    }

    override fun handle(inquiry: Inquiry): Reply {
        return commandHandlerFacade.handle(inquiry)
    }

    override fun setUpdateListener(listener: (update: Update) -> Unit) {
        publisher
                .updatesObservable()
                .subscribe { listener(it) }
    }

    override fun transferFunds(channelId: String, address: String, payload: PurchaseHandler.Payload): Reply {
        return purchaseFacade.transferFunds(channelId, address, payload)
    }

}