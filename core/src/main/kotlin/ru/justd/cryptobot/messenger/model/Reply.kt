package ru.justd.cryptobot.messenger.model

data class Reply constructor(
        val channelId: String,
        val text: String,
        val dialog: Dialog? = null,
        val invoice: Invoice? = null
)

//todo make sealed class with 3 types: PlainTextReply, DialogReply, InvoiceReply