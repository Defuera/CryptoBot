package ru.justd.cryptobot.persistance

interface FeedbackStorage {
    fun store(feedback: String)
}