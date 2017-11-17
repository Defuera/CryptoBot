package ru.justd.cryptobot

import ru.justd.cryptobot.di.DaggerCryptoCoreComponent
import ru.justd.cryptobot.di.StorageModule
import ru.justd.cryptobot.handler.CommandHandlerFacade
import ru.justd.cryptobot.handler.CommandHandlerFactory
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.publisher.Publisher
import ru.justd.cryptobot.publisher.Update
import javax.inject.Inject

class CryptoCore private constructor(debug : Boolean){

    companion object {
        fun start(debug : Boolean = false) = CryptoCore(debug)
    }


    @Inject
    lateinit var publisher: Publisher

    @Inject
    lateinit var commandHandlerFacade: CommandHandlerFacade

    init {
        DaggerCryptoCoreComponent.builder()
                .storageModule(StorageModule(debug))
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
                .observeUpdates()
                .subscribe { listener(it) }
    }


}