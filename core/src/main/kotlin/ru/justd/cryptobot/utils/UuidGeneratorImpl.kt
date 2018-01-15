package ru.justd.cryptobot.utils

import java.util.*

object UuidGeneratorImpl : UuidGenerator {

    override fun random() = UUID.randomUUID().toString()

}