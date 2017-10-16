package ru.justd.cryptobot.messenger.model

interface ResponseCase<T> {

    fun appearance(): Appearance

    fun title(): String

    enum class Appearance {
        FULL, HALF, THIRD, QUARTER
    }

}