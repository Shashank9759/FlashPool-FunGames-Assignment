package com.flashpool

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.flashpool.data.worker.AnalyticsUploadWorker
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class FlashPoolApp : Application(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        initializeFirebase()
        scheduleAnalyticsUpload()
    }

    private fun initializeFirebase() {
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }
        val appName = FirebaseApp.getInstance().name
        Log.i(TAG, "Firebase ready (app=$appName) — analytics sync will write to Firestore")
    }

    private fun scheduleAnalyticsUpload() {
        // PeriodicWorkRequest minimum interval enforced by Android is 15 minutes — expected, not a bug
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresCharging(true)
            .build()

        val request = PeriodicWorkRequestBuilder<AnalyticsUploadWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            ANALYTICS_WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    companion object {
        private const val ANALYTICS_WORK_NAME = "analytics_upload"
        private const val TAG = "FlashPoolApp"
    }
}
