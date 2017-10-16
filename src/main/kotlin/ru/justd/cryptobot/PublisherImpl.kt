package ru.justd.cryptobot

import ru.justd.cryptobot.persistance.Storage

class PublisherImpl(val storage: Storage) : Publisher {

    //todo onUserPreferencesUpdate should be restarted
    init {
        run()
    }

    private fun run() {

//        for (storage.getSubscriptions())
//            Thread(Runnable {
//
//            })

    }

}