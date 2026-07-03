package com.flashpool.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flashpool.data.local.dao.AnalyticsDao
import com.flashpool.data.local.dao.ProductDao
import com.flashpool.data.local.entity.AnalyticsLogEntity
import com.flashpool.data.local.entity.ProductEntity

@Database(
    entities = [ProductEntity::class, AnalyticsLogEntity::class],
    version = 1,
    exportSchema = true
)
abstract class FlashPoolDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun analyticsDao(): AnalyticsDao
}
