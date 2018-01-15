package ru.justd.cryptobot.api.blockchain.bitcoin

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

object BitcoinAddressValidator {

    private val ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"

    fun validateAddress(addr: String): Boolean {
        if (addr.length < 26 || addr.length > 35)
            return false
        val decoded = decodeBase58To25Bytes(addr) ?: return false

        val hash1 = sha256(Arrays.copyOfRange(decoded, 0, 21))
        val hash2 = sha256(hash1)

        return Arrays.equals(Arrays.copyOfRange(hash2, 0, 4), Arrays.copyOfRange(decoded, 21, 25))
    }

    private fun decodeBase58To25Bytes(input: String): ByteArray? {
        var num = BigInteger.ZERO
        for (t in input.toCharArray()) {
            val p = ALPHABET.indexOf(t)
            if (p == -1)
                return null
            num = num.multiply(BigInteger.valueOf(58)).add(BigInteger.valueOf(p.toLong()))
        }

        val result = ByteArray(25)
        val numBytes = num.toByteArray()
        System.arraycopy(numBytes, 0, result, result.size - numBytes.size, numBytes.size)
        return result
    }

    private fun sha256(data: ByteArray): ByteArray {
        try {
            val md = MessageDigest.getInstance("SHA-256")
            md.update(data)
            return md.digest()
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalStateException(e)
        }

    }

    @JvmStatic
    fun main(args: Array<String>) {
        assertBitcoin("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", true)
        assertBitcoin("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j", false)
        assertBitcoin("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK9", true)
        assertBitcoin("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62X", false)
        assertBitcoin("1ANNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", false)
        assertBitcoin("1A Na15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i", false)
        assertBitcoin("BZbvjr", false)
        assertBitcoin("i55j", false)
        assertBitcoin("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!", false)
        assertBitcoin("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62iz", false)
        assertBitcoin("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62izz", false)
        assertBitcoin("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9", false)
        assertBitcoin("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I", false)
    }

    private fun assertBitcoin(address: String, expected: Boolean) {
        val actual = validateAddress(address)
        if (actual != expected)
            throw AssertionError(String.format("Expected %s for %s, but got %s.", expected, address, actual))
    }
}