package ru.justd.cryptobot.publisher

import io.reactivex.Observable

/**
 * Publish updates for users created subscriptions
 */
interface Publisher {

    fun observeUpdates() : Observable<Update>

}