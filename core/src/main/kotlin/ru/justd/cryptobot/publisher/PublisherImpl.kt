package ru.justd.cryptobot.publisher


import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import ru.justd.cryptobot.api.exchanges.ExchangeApiFacade
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.persistance.Storage

internal class PublisherImpl constructor(
        private val exchangeFacade: ExchangeApiFacade,
        storage: Storage
) : Publisher {

    private val subject = BehaviorSubject.create<Update>()

    init {

        val observeUpdates = storage.observeUpdates()
        observeUpdates
                .subscribe(
                        { update ->
                            val preferences = update.userPreferences
                            preferences.subscriptions.forEach {
                                initWorker(update.userId, it)
                            }
                        }
                )
    }

    override fun observeUpdates(): Observable<Update> = subject //todo identify subscriber to filter messages (subscribers should not get messages of other subscribers)

    private fun initWorker(channelId: String, subscription: Subscription) {
        Thread(Runnable {
            //todo rx worker
            print("new thread started")
            val response = exchangeFacade.getRate(subscription.base, subscription.target, subscription.exchange)
            publishUpdate(channelId, response)

            Thread.sleep(subscription.periodicityMins * 1000 * 60)
            initWorker(channelId, subscription)
        }).start()
    }

    private fun publishUpdate(channelId: String, rate: RateResponse) {
        subject.onNext(Update(channelId, createMessage(rate)))
    }

    private fun createMessage(rate: RateResponse): String { //todo this is copied from PriceHandler
        return try {
            "${rate.base} price is ${rate.amount} ${rate.target}"
        } catch (error: ExchangeNotSupported) {
            "${error.exchange} exchange not supported" //todo log to be aware what exchanges customers are waiting the most, localize
        } catch (error: RequestFailed) {
            error.message
        }.trim()
    }

}