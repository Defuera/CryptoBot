package ru.justd.cryptobot

import ru.justd.cryptobot.di.DaggerCryptoCoreComponent
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.FeedbackStorage
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.Update
import javax.inject.Inject

class CryptoCore private constructor(clientName: String, debug: Boolean, feedbackStorage: FeedbackStorage) {

    companion object {
        fun start(clientName: String, debug: Boolean = true, feedbackStorage: FeedbackStorage) = CryptoCore(clientName, debug, feedbackStorage)
    }


    @Inject
    lateinit var publisher: Publisher

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    init {
        DaggerCryptoCoreComponent.builder()
                .storageModule(StorageModule(clientName, debug, feedbackStorage))
                .build()
                .inject(this)
    }

    fun addCommandHandler(commandHandlerFactory: CommandHandlerFactory<*>) {
        commandHandlerFacade.addCommandHandler(commandHandlerFactory)

    }

    fun handle(channelId: String, request: String): Reply {
        return commandHandlerFacade.handle(channelId, request)
    }

    fun setUpdateListener(listener: (update: Update) -> Unit) {
        publisher
                .updatesObservable()
                .subscribe { listener(it) }
    }


}