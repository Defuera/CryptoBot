package ru.justd.cryptobot.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class LogFormatterTest {

    val testInstance = LogFormatter

    @Test
    fun formatText() {
        val formattedText = testInstance.formatText(
"""*ecff53d5-1c58-497d-a5fd-384a43b1606f*
_thread: ForkJoinPool.commonPool-worker-1_

Choose crypto"""
        )

        assertThat(formattedText).isEqualTo("*ecff53d5-1c58-497d-a5fd-384a43b1606f*\\n_thread: ForkJoinPool.commonPool-worker-1_\\n\\nChoose crypto")
    }
}