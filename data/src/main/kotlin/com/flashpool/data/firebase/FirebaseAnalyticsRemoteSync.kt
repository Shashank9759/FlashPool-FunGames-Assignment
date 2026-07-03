package com.flashpool.data.firebase

import android.util.Log
import com.flashpool.domain.model.AnalyticsEventPayload
import com.flashpool.domain.repository.AnalyticsRemoteSync
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAnalyticsRemoteSync @Inject constructor(
    private val firestore: FirebaseFirestore
) : AnalyticsRemoteSync {

    override suspend fun uploadEvents(events: List<AnalyticsEventPayload>): Result<Unit> {
        if (events.isEmpty()) return Result.success(Unit)

        return try {
            Log.d(TAG, "Starting Firebase sync for ${events.size} analytics event(s)")
            val batch = firestore.batch()
            val collection = firestore.collection(COLLECTION)

            events.forEach { event ->
                val docRef = collection.document()
                val payload = mapOf(
                    "localId" to event.localId,
                    "eventType" to event.eventType,
                    "productId" to event.productId,
                    "timestampEpochMillis" to event.timestampEpochMillis,
                    "syncedAtEpochMillis" to System.currentTimeMillis()
                )
                batch.set(docRef, payload)
                Log.d(
                    TAG,
                    "Queued event localId=${event.localId} type=${event.eventType} product=${event.productId}"
                )
            }

            batch.commit().await()
            Log.i(TAG, "Firebase sync complete — uploaded ${events.size} event(s) to $COLLECTION")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Firebase sync failed for ${events.size} event(s)", e)
            Result.failure(e)
        }
    }

    companion object {
        private const val TAG = "FlashPoolFirebaseSync"
        private const val COLLECTION = "analytics_log"
    }
}
