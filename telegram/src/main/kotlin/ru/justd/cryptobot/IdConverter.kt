package ru.justd.cryptobot


fun toChannelId(chatId: Long) = chatId.toString()

fun toChatId(channelId: String) = channelId.toLong()
