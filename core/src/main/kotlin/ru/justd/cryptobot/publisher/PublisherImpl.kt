package ru.justd.cryptobot.publisher


import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.handler.price.PriceCommandHandler
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.persistance.Storage
import utils.DateManager
import utils.DateManagerImpl.MILLIS_IN_MINUTE

private const val UPDATE_LOOP_DELAY_MILLIS = 1 * MILLIS_IN_MINUTE //every minute

internal class PublisherImpl (
        private val exchangeFacade: ExchangeApiFacade,
        private val storage: Storage,
        private val dateManager: DateManager
) : Publisher {

    private val subject = BehaviorSubject.create<Update>()

    init {
        initWorker()
    }

    override fun updatesObservable(): Observable<Update> = subject

    private fun initWorker() {

        launch {
            delay(UPDATE_LOOP_DELAY_MILLIS)
            initWorker()
        }


        storage
                .getSubscriptions()
                ?.filter { dateManager.hasTime(it.publishTimes) }
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