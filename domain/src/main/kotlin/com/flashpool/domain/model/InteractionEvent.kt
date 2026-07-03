package com.flashpool.domain.model

sealed class InteractionEvent {
    abstract val productId: String
    abstract val timestampEpochMillis: Long

    data class JoinPool(
        override val productId: String,
        override val timestampEpochMillis: Long
    ) : InteractionEvent()

    data class ShareLink(
        override val productId: String,
        override val timestampEpochMillis: Long
    ) : InteractionEvent()

    data class CompletedCheckout(
        override val productId: String,
        override val timestampEpochMillis: Long
    ) : InteractionEvent()
}
