package ru.justd.cryptobot.api.exchanges.gdax

import com.google.gson.annotations.SerializedName
import okhttp3.Headers
import okhttp3.OkHttpClient
import ru.justd.cryptobot.CoreConfig
import ru.justd.cryptobot.api.PurchaseApi
import ru.justd.cryptobot.api.exchanges.PollingExchange
import ru.justd.cryptobot.api.exchanges.RateResponse
import ru.justd.cryptobot.api.exchanges.exceptions.RequestFailed
import ru.justd.cryptobot.api.exchanges.gdax.model.TransferFailed
import ru.justd.cryptobot.messenger.model.Reply
import java.time.Instant
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.management.RuntimeErrorException


/**
 * https://docs.gdax.com/
 */
class GdaxApi(val okHttpClient: OkHttpClient) : PollingExchange(okHttpClient), PurchaseApi {

    companion object {
        const val NAME = "GDAX"

        private const val BASE_URL = "https://api.gdax.com"

        private const val CB_ACCESS_KEY = "CB-ACCESS-KEY" // The api key as a string.
        private const val CB_ACCESS_SIGN = "CB-ACCESS-SIGN" // The base64-encoded signature (see Signing a Message).
        private const val CB_ACCESS_TIMESTAMP = "CB-ACCESS-TIMESTAMP" // A timestamp for your request.
        private const val CB_ACCESS_PASSPHRASE = "CB-ACCESS-PASSPHRASE" // The passphrase you specified when creating the API key

        private val SHARED_MAC = Mac.getInstance("HmacSHA256")
    }

    private val BASE_URL = "https://api.gdax.com"

    /**
     * https://docs.gdax.com/#get-product-order-book
     */
    override fun getRateUrl(base: String, target: String) = "$BASE_URL/products/$base-$target/book"

    @Throws(RequestFailed::class)
    override fun parseRateResponseBody(bodyString: String, base: String, target: String): RateResponse {
        val envelope = gson.fromJson<Envelope>(bodyString, Envelope::class.java)
        if (envelope.bids != null) {
            return RateResponse(envelope.bids[0][0].toDouble(), base, target)
        } else {
            throw RequestFailed(envelope.errorMessage!!)
        }
    }

    @Suppress("ArrayInDataClass")
    private data class Envelope(

            @SerializedName("message")
            val errorMessage: String?,
            val sequence: Long?,
            /**
             *  [ price, size, num-orders ]
             */
            val bids: Array<Array<String>>?,
            /**
             * [ price, size, num-orders ]
             */
            val asks: Array<Array<String>>?
    )

    @Throws(TransferFailed::class)
    override fun transferFunds(channelId: String, base: String, amount: Double, address: String): Reply {
        return Reply(
                channelId,
                "$amount $base has been transferred to $address. (not for real, cause this is just test)\n" +
                        "You can track the transaction here ${getBlockchainInfoUrl(base)}$address. It should appear in next 30 minutes."
        )

//        val endpoint = "/withdrawals/crypto"
//        val url = "${Companion.BASE_URL}$endpoint"
//        val method = "POST"
//
//        val jsonBody = "{\n" +
//                "    \"amount\": $amount,\n" +
//                "    \"currency\": \"$base\",\n" +
//                "    \"crypto_address\": \"$address\"\n" +
//                "}"
//
//        val response = okHttpClient
//                .newCall(
//                        Request.Builder()
//                                .post(
//                                        RequestBody.create(
//                                                MediaType.parse("application/json; charset=utf-8"),
//                                                jsonBody
//                                        )
//                                )
//                                .headers(securityHeaders(endpoint, method, jsonBody))
//                                .url(url)
//                                .build()
//                )
//                .execute()
//
//        val responseBody = response.body()?.string()
//        if (response.isSuccessful) {
//            ShiffrLogger.log("GdaxApi#transferFunds", "success")
//            val purchaseResult = gson.fromJson(responseBody, PurchaseResult::class.java)
//
//            return Reply(
//                    channelId,
//                    "${purchaseResult.amount} ${purchaseResult.currency} has been transferred to $address.\n" +
//                            "You can track the transaction here ${getBlockchainInfoUrl(base)}/$address. It should appear in next 30 minutes."
//            )
//        } else {
//            val errorMessage = "error: $responseBody"
//            ShiffrLogger.log("GdaxApi#transferFunds", errorMessage)
//            throw TransferFailed(channelId, base, amount, address, errorMessage)
//        }

    }

    private fun getBlockchainInfoUrl(base: String) =
            when (base) {
                "BTC" -> "https://blockchain.info/address"
                "ETH" -> "https://www.etherchain.org/account"
                "LTC" -> "https://live.blockcypher.com/btc"
                else -> "error"
            }


    /**
     * The CB-ACCESS-SIGN header is generated by creating a sha256 HMAC using
     * the base64-decoded secret key on the prehash string for:
     * timestamp + method + requestPath + body (where + represents string concatenation)
     * and base64-encode the output.
     * The timestamp value is the same as the CB-ACCESS-TIMESTAMP header.
     * @param requestPath
     * @param method
     * @param body
     * @param timestamp
     * @return
     */
    private fun signRequest(requestPath: String, method: String, body: String?, timestamp: String): String {
        try {
            val prehash = timestamp + method.toUpperCase() + requestPath + (body ?: "")
            val secretDecoded = Base64.getDecoder().decode(CoreConfig.GDAX_SECRET)
            val keyspec = SecretKeySpec(secretDecoded, "HmacSHA256")
            val sha256 = SHARED_MAC.clone() as Mac
            sha256.init(keyspec)
            return Base64.getEncoder().encodeToString(sha256.doFinal(prehash.toByteArray()))
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
            throw RuntimeErrorException(Error("Cannot set up authentication headers."))
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeErrorException(Error("Cannot set up authentication headers."))
        }

    }

    private fun securityHeaders(endpoint: String, method: String, jsonBody: String?): Headers {
        val timestamp = Instant.now().epochSecond.toString() + ""

        val headers = Headers.of(
                "accept", "application/json",
                "content-type", "application/json",
                CB_ACCESS_KEY, CoreConfig.GDAX_KEY,
                CB_ACCESS_SIGN, signRequest(endpoint, method, jsonBody, timestamp),
                CB_ACCESS_TIMESTAMP, timestamp,
                CB_ACCESS_PASSPHRASE, CoreConfig.GDAX_LICENCE
        )

        return headers
    }
}