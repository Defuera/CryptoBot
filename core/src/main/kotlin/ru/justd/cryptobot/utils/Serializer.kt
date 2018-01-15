package ru.justd.cryptobot.utils

import ru.justd.cryptobot.handler.purchase.PurchaseHandler

object Serializer {

//    val gson = Gson()

    fun serialize(obj: PurchaseHandler.Payload): String {
//        val toJson = gson.toJson(obj)
//        val toByteArray = toJson.toByteArray()
//        return Base64.getEncoder().encodeToString(toByteArray)
        return "${obj.base}_${obj.baseAmount}_${obj.target}_${obj.targetAmount}"
    }

    fun deserialize(serializedObj: String): PurchaseHandler.Payload {
//    val byteArray = Base64.getDecoder().decode(serializedObj)
//    return gson.fromJson(String(byteArray), T::class.java)
        val args = serializedObj.split("_")
        return PurchaseHandler.Payload(
                args[0],
                args[1].toDouble(),
                args[2],
                args[3].toInt()
        )
    }

}