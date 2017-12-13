package ru.justd.cryptobot.handler.trader

import ru.justd.cryptobot.api.exchanges.ExchangeFeedFacade
import ru.justd.cryptobot.api.exchanges.gdax.GdaxApi
import ru.justd.cryptobot.api.exchanges.gdax.Order
import ru.justd.cryptobot.handler.CommandHandler
import ru.justd.cryptobot.messenger.model.Dialog
import ru.justd.cryptobot.messenger.model.Option
import ru.justd.cryptobot.messenger.model.Reply

class TradeHandler(
        private val exchangeFeedFacade: ExchangeFeedFacade,
        private val gdaxApi: GdaxApi,
        private val dialogTag: String
) : CommandHandler {


    private val TAG_LIST_FULFILLED_ORDERS = "list_fulfilled_orders"
    private val TAG_LIST_OPEN_ORDERS = "list_open_orders"
    private val TAG_ORDER_OPTIONS = "order_options"
    private val TAG_CANCEL_ORDER = "cancel_order"
//    private val TAG_BACK = "order_options"

    override fun createReply(channelId: String): Reply {

//        SimpleRobot(exchangeFeedFacade, gdaxApi)

        if (dialogTag == TAG_LIST_FULFILLED_ORDERS) {
            val orders = gdaxApi.getOrders().filter { it.isFulfilled() }
            return Reply(
                    channelId,
                    "Here's your fulfilled orders",
                    Dialog(
                            "/trade $TAG_ORDER_OPTIONS",
                            orders.map { Option(printOrder(it), it.id) }
                                    + Option("Back", "")
                    )
            )
        }

        if (dialogTag == TAG_LIST_OPEN_ORDERS) {
            val orders = gdaxApi.getOrders().filter { !it.isFulfilled() }
            return Reply(
                    channelId,
                    "Here's your open orders",
                    Dialog(
                            "/trade $TAG_ORDER_OPTIONS",
                            orders.map { Option(printOrder(it), it.id) }
                                    + Option("Back", "")
                    )
            )
        }

        if (dialogTag.contains(TAG_ORDER_OPTIONS)) {
            return Reply(
                    channelId,
                    "Here's your open orders",
                    Dialog(
                            "/trade $TAG_CANCEL_ORDER",
                            listOf(
                                    Option("Cancel order",  retrieveOrderId(TAG_ORDER_OPTIONS)),
                                    Option("Back", "")
                            )
                    )
            )
        }

        if (dialogTag.contains(TAG_CANCEL_ORDER)) {
            gdaxApi.cancelOrder(retrieveOrderId(TAG_CANCEL_ORDER))
            return Reply(channelId, "Order has been canceled")
        }

        return Reply(
                channelId,
                "What you want to do?",
                Dialog(
                        "/trade",
                        listOf(
                                Option("List open orders", TAG_LIST_OPEN_ORDERS),
                                Option("List fulfilled orders", TAG_LIST_FULFILLED_ORDERS)
                        )
                )
        )

    }

    private fun retrieveOrderId(tag: String): String {
        return dialogTag.replace(tag, "").trim()
    }

    private fun printOrder(order: Order): String {
        return "${order.size} ${order.price} ${order.created_at} ${order.status}"
    }

}