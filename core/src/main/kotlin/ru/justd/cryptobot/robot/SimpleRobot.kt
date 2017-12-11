package ru.justd.cryptobot.robot

import io.reactivex.functions.Consumer
import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.api.exchanges.ShiffrTicker
import utils.ShiffrLogger

class SimpleRobot(exchangeFeedFacade: ExchangeFeedFacade) {

    init {
        print("robot created")
        exchangeFeedFacade.subscribe(
                onNext = Consumer { analyze(it) },
                onError = Consumer { throwable -> ShiffrLogger.log("SimpleRobot", throwable) }
        )
    }

    private fun analyze(ticker: ShiffrTicker) {
        print("take decision regarding update $ticker")
    }

}