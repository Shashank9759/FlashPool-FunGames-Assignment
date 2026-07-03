package com.flashpool.domain.model

data class AnalyticsEventPayload(
    val localId: Long,
    val eventType: String,
    val productId: String,
    val timestampEpochMillis: Long
)
