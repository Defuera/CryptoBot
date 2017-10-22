package ru.justd.cryptobot.publisher


import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.handler.subscribe.Subscription
import ru.justd.cryptobot.messenger.Messenger
import ru.justd.cryptobot.persistance.Storage

internal class PublisherImpl(
        private val messenger: Messenger,
        private val exchangeFacade: ExchangeFacade,
        storage: Storage
) : Publisher {

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

    private fun initWorker(channelId: String, subscription: Subscription) {
        Thread(Runnable {
            print("new thread started")
            val response = exchangeFacade.getRate(subscription.base, subscription.target, subscription.exchange)
            publishUpdate(channelId, response)
            Thread.sleep(subscription.periodicityMins * 1000 * 60)
            initWorker(channelId, subscription)
        }).start()
    }

    private fun publishUpdate(channelId: String, rate: RateResponse) {
        sendMessage(channelId, createMessage(rate))
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

    private fun sendMessage(channelId: String, text: String) {
        messenger.sendMessage(channelId, text)
    }

}