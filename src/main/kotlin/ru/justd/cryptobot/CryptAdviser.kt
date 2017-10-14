package ru.justd.cryptobot

/**
 * Common interface for all the CryptAdviser implementations - either it's telegram bot or google assistent interface, or a console program.
 */
interface CryptAdviser {

    /**
     * Replies to user request. User request usually is a command from a predifined set.
     * If request cannot be handled error message will be returned, otherwise correspondent message will be returnt as a response.
     */
    fun handleCommand(requestMessage: String): String

    fun publishUpdate()
}