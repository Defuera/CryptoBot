package ru.justd.cryptobot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage
import ru.justd.cryptobot.exchanges.ExchangeFacade
import ru.justd.cryptobot.exchanges.RateResponse
import ru.justd.cryptobot.exchanges.exceptions.ExchangeNotSupported
import ru.justd.cryptobot.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.persistance.Storage

class PublisherImpl(
        private val telegramBot: TelegramBot,
        private val exchangeFacade: ExchangeFacade,
        private val storage: Storage
) : Publisher {

    //todo onUserPreferencesUpdate should be restarted
    init {
        run()
        storage.subscribeToUpdates { userPreferences -> run() } //todo
    }

    private fun run() {

        val subscriptionsByChatId = storage.getSubscriptionsByChatId()
        subscriptionsByChatId.forEach { chatId, subscriptions ->
            val subscription = subscriptions[0] //todo learn to make batch calls
            Thread(Runnable {
                print("new thread started")
                val response = exchangeFacade.getRate(subscription.base, subscription.target, subscription.exchange)
                publishUpdate(chatId, response)
                Thread.sleep(5 * 1000)
            }).start()

        }

    }

    fun publishUpdate(chatId: String, rate: RateResponse) {
        sendMessage(chatId.toLong(), createMessage(rate))
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