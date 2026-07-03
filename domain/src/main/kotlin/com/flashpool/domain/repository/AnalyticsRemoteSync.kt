package com.flashpool.domain.repository

import com.flashpool.domain.model.AnalyticsEventPayload

interface AnalyticsRemoteSync {
    suspend fun uploadEvents(events: List<AnalyticsEventPayload>): Result<Unit>
}
