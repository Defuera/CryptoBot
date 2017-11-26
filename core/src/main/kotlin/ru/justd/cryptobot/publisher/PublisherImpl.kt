package ru.justd.cryptobot.publisher


import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.price.PriceCommandHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage
import utils.TimeManager


internal class PublisherImpl (
        private val exchangeFacade: ExchangeApiFacade,
        private val storage: Storage,
        private val timeManager: TimeManager
) : Publisher {

    private val subject = BehaviorSubject.create<Update>()

    init {
        initWorker()
    }

    override fun updatesObservable(): Observable<Update> = subject

    private fun initWorker() {
        launch {
            delay(timeManager.getUpdatesPeriod())
            initWorker()
        }

        storage
                .getAllSubscriptions()
                ?.filter { timeManager.isTimeToPublish(it) }
                ?.forEach {

                    launch {
                        val resp = PriceCommandHandler(exchangeFacade, it.base, it.target, it.exchange).createReply(it.channelId)
                        publishUpdate(it.channelId, resp)
                    }
                }

    }

    private fun publishUpdate(channelId: String, reply: Reply) {
        subject.onNext(Update(channelId, reply.text))
    }

}