package utils

import java.util.*

object UuidGeneratorImpl : UuidGenerator {

    override fun random() = UUID.randomUUID().toString()

}