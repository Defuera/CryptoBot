package ru.justd.cryptobot.handler

import ru.justd.cryptobot.BuildConfig
import ru.justd.cryptobot.messenger.model.Reply

object StartHandler : CommandHandler {
    override fun createReply(channelId: String) =
            Reply(channelId,
                    """
                        |${BuildConfig.BOT_NAME} is your buddy in the cryptocurrencies world.
                        |It helps you be up to date with popular coins prices. You can fetch prices via `/price` command or `/subscribe` for updates.
                        |To see full list of commands just start typing `/`.
                        |
                        |${BuildConfig.BOT_NAME} is an open source software.
                        /|"""
                            .trimMargin()
            )
}