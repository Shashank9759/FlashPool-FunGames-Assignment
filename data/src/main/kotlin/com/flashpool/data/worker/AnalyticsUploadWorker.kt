package com.flashpool.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.flashpool.domain.repository.AnalyticsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class AnalyticsUploadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val analyticsRepository: AnalyticsRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d(TAG, "WorkManager analytics upload worker started")
        return analyticsRepository.uploadPendingEvents()
            .fold(
                onSuccess = { syncedCount ->
                    Log.i(TAG, "WorkManager analytics upload finished — synced $syncedCount event(s)")
                    Result.success()
                },
                onFailure = { error ->
                    Log.w(TAG, "WorkManager analytics upload failed — scheduling retry", error)
                    Result.retry()
                }
            )
    }

    companion object {
        private const val TAG = "AnalyticsUploadWorker"
    }
}
