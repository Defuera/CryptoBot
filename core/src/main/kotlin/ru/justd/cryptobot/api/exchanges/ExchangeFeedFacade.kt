package ru.justd.cryptobot.api.exchanges

import io.reactivex.functions.Consumer

class ExchangeFeedFacade(
        private val gdaxFeed: ExchangeFeed
) {

    fun subscribe(onNext: Consumer<ShiffrTicker>, onError: Consumer<Throwable>) =

            gdaxFeed.observable().subscribe(onNext, onError)

}