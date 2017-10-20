package ru.justd.cryptobot.publisher

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import ru.justd.cryptobot.TelegramCryptAdviser
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.persistance.Storage
import ru.justd.cryptobot.persistance.Subscription

class PublisherImpl(
        private val telegramBot: TelegramBot,
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
            Thread.sleep(subscription.periodicityMins)
        }).start()
    }

    private fun publishUpdate(channelId: String, rate: RateResponse) {
        sendMessage(channelId.toLong(), createMessage(rate))
    }

    private fun createMessage(rate: RateResponse): String { //todo this is copied from PriceHandler
        return try {
            //            val rate = exchangeFacade.getRate(base, target, exchange)
            "${rate.base} price is ${rate.amount} ${rate.target}"
        } catch (error: ExchangeNotSupported) {
            "${error.exchange} exchange not supported" //todo log to be aware what exchanges customers are waiting the most, localize
        } catch (error: RequestFailed) {
            error.message
        }.trim()
    }


    private fun sendMessage(chatId: Long, outgoingMessage: String) {
        println("send message...")
        telegramBot.execute(SendMessage(chatId, createOutgoingMessage(outgoingMessage)).parseMode(ParseMode.Markdown))
    }


    private fun createOutgoingMessage(message: String) =
            "*${TelegramCryptAdviser.INSTANCE_ID}*\n\n$message"


}