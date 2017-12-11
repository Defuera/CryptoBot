package ru.justd.cryptobot.api.exchanges.gdax

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.*
import ru.justd.cryptobot.api.exchanges.ExchangeFeed
import ru.justd.cryptobot.api.exchanges.ShiffrTicker
import utils.ShiffrLogger

class GdaxFeed(client: OkHttpClient) : ExchangeFeed {


    companion object {
        const val BASE_URL = "wss://ws-feed.gdax.com"
    }

    private val subject = PublishSubject.create<ShiffrTicker>()

    private val gson = Gson()

    init {
        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                //connect to ticker channel
                webSocket.send("""{"type": "subscribe", "channels": [{ "name": "ticker", "product_ids": ["BTC-EUR"] }]}""")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                ShiffrLogger.log("GdaxFeed#onFailure", t)
            }

            override fun onMessage(webSocket: WebSocket?, text: String?) {
                val ticker = gson.fromJson<Ticker>(text, Ticker::class.java)
                subject.onNext(ShiffrTicker(GdaxApi.NAME, "BTC", "EUR", ticker.price))
            }

        }

        client.newWebSocket(Request.Builder().url(BASE_URL).build(), listener)

    }

    override fun observable(): Observable<ShiffrTicker> = subject

}

