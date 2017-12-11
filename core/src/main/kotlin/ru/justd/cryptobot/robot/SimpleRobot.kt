package ru.justd.cryptobot.robot

import io.reactivex.functions.Consumer
import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.api.exchanges.ShiffrTicker
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import utils.ShiffrLogger

class SimpleRobot(exchangeFeedFacade: ExchangeFeedFacade, private val gdaxApi: GdaxApi) {

    init {
        print("robot created")
        exchangeFeedFacade.subscribe(
                onNext = Consumer { analyze(it) },
                onError = Consumer { throwable -> ShiffrLogger.log("SimpleRobot", throwable) }
        )
    }

    private fun analyze(ticker: ShiffrTicker) {
        println("take decision regarding update $ticker")

        val orders = gdaxApi.getOrders()
        orders.forEach {
            println(it)
        }
    }

}