package utils

object ShiffrLogger {
    fun log(tag: String, throwable: Throwable) {
        throwable.printStackTrace()
    }

    fun log(tag: String, error : String) {
        println("$tag: $error")
    }
}