package com.flashpool.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val title: String,
    val imageUrl: String,
    val retailPrice: Double,
    val inventoryCount: Int,
    val category: String,
    val dealEndEpochMillis: Long?
)

@Entity(tableName = "analytics_log")
data class AnalyticsLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val eventType: String,
    val productId: String,
    val timestampEpochMillis: Long,
    val synced: Boolean = false
)
