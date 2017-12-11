package ru.justd.cryptobot.api.exchanges

import io.reactivex.Observable


interface ExchangeFeed {
    fun observable(): Observable<ShiffrTicker>
}