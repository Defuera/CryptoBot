package ru.justd.cryptobot.handler

import ru.justd.cryptobot.messenger.model.Reply

class AboutHandler  : CommandHandler {
    override fun createReply(channelId: String): Reply {

        return Reply(channelId,
"""
For legal information please refer to our website https://defuera.github.io/CryptoBot
Or jump to:
[Privacy policy](https://defuera.github.io/CryptoBot#privacy_policy)
[Terms of service](https://defuera.github.io/CryptoBot#terms_of_service)
[Payments and Refund Policy](https://defuera.github.io/CryptoBot#payment_and_refund_policy)
[Contact information](https://defuera.github.io/CryptoBot#contact_us)

"""
                        .trimMargin()
        )
    }
}