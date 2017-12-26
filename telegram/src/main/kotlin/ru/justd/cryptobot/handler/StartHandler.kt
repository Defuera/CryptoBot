package ru.justd.cryptobot.handler

import ru.justd.cryptobot.analytics.Analytics
import ru.justd.cryptobot.messenger.model.Reply
import ru.justd.cryptobot.telegram.BuildConfig

/**
 * /start command is a required global command https://core.telegram.org/bots#global-commands
 */
class StartHandler(private val analytics: Analytics) : CommandHandler {
    override fun createReply(channelId: String): Reply {

        analytics.trackStart(channelId)
        return Reply(channelId,
"""
/price - get price of given pair from one of supported exchanges
/subscribe - receive periodical updates on price for specified pair
/unsubscribe - remove one of previously created subscriptions
/addressinfo - get info on given Bitcoin, Litecoin or Ethereum address
/feedback - send us your thanks, suggestions and questions
"""
                        .trimMargin()
        )
    }
}