package ru.justd.cryptobot.api.blockchain

data class AddressInfo(
        val balance: String,
        val confirmed_balance: String,
        val received: String,
        val sent: String,
        val pending: String,
        val multisigReceived: String,
        val multisigSent: String,
        val txReceived: String,
        val txSent: String,
        val txMultisig_received: String,
        val txMultisig_sent: String,
        val txUnconfirmed: String,
        val txInvalid: String
)