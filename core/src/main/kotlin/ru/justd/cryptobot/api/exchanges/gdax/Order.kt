package ru.justd.cryptobot.api.exchanges.gdax


/**
 * Created by robevansuk on 03/02/2017.
 */
data class Order(
        val id: String,
        val price: String,
        val size: String,
        val product_id: String,
        val side: String,
        val stp: String,
        val type: String,
        val time_in_force: String,
        val post_only: String,
        val created_at: String,
        val fill_fees: String,
        val filled_size: String,
        val executed_value: String,
        /**
         * open, done, rejected, active, pending
         */
        val status: String,
        val settled: Boolean?
) {
    fun isFulfilled() = status == "done" || status == "rejected"
}