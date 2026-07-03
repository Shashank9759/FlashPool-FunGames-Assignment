package com.flashpool.domain.usecase

import app.cash.turbine.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountdownTickerTest {

    @Test
    fun `remaining millis computed from end instant each tick`() = runTest {
        var now = 1_000_000L
        val end = now + 5_000L
        val ticker = CountdownTicker(endEpochMillis = end) { now }

        ticker.remainingMillisFlow().test {
            assertEquals(5_000L, awaitItem())
            now += 1_000L
            advanceTimeBy(1_000)
            assertEquals(4_000L, awaitItem())
            now += 2_500L
            advanceTimeBy(1_000)
            assertEquals(1_500L, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emits zero and completes when past end`() = runTest {
        var now = 10_000L
        val ticker = CountdownTicker(endEpochMillis = 10_000L) { now }

        ticker.remainingMillisFlow().test {
            assertEquals(0L, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `self corrects drift instead of decrementing a counter`() = runTest {
        var now = 0L
        val end = 3_000L
        val ticker = CountdownTicker(endEpochMillis = end) { now }

        ticker.remainingMillisFlow().test {
            assertEquals(3_000L, awaitItem())
            now += 1_000L
            advanceTimeBy(1_000)
            assertEquals(2_000L, awaitItem())
            now += 5_000L // clock jumped forward
            advanceTimeBy(1_000)
            val remaining = awaitItem()
            assertTrue(remaining == 0L)
            awaitComplete()
        }
    }
}
