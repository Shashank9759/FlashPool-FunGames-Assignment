package com.flashpool.data.repository

import android.util.Log
import com.flashpool.data.local.dao.AnalyticsDao
import com.flashpool.data.mapper.toEntity
import com.flashpool.domain.model.AnalyticsEventPayload
import com.flashpool.domain.model.InteractionEvent
import com.flashpool.domain.repository.AnalyticsRemoteSync
import com.flashpool.domain.repository.AnalyticsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AnalyticsRepositoryImpl(
    private val analyticsDao: AnalyticsDao,
    private val remoteSync: AnalyticsRemoteSync,
    private val ioDispatcher: CoroutineDispatcher
) : AnalyticsRepository {

    override suspend fun logEvent(event: InteractionEvent): Unit = withContext(ioDispatcher) {
        analyticsDao.insert(event.toEntity())
    }

    override suspend fun uploadPendingEvents(): Result<Int> = withContext(ioDispatcher) {
        val pending = analyticsDao.getUnsynced()
        if (pending.isEmpty()) {
            Log.d(TAG, "No pending analytics events to upload")
            return@withContext Result.success(0)
        }

        Log.d(TAG, "Uploading ${pending.size} pending analytics event(s) via WorkManager")
        val payloads = pending.map {
            AnalyticsEventPayload(
                localId = it.id,
                eventType = it.eventType,
                productId = it.productId,
                timestampEpochMillis = it.timestampEpochMillis
            )
        }

        remoteSync.uploadEvents(payloads).fold(
            onSuccess = {
                analyticsDao.markSynced(pending.map { row -> row.id })
                Log.i(TAG, "Marked ${pending.size} event(s) as synced locally")
                Result.success(pending.size)
            },
            onFailure = { error ->
                Log.w(TAG, "Remote sync failed — ${pending.size} event(s) left for retry", error)
                Result.failure(error)
            }
        )
    }

    companion object {
        private const val TAG = "AnalyticsRepository"
    }
}
