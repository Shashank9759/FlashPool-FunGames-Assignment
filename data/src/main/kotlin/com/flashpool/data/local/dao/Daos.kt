package com.flashpool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flashpool.data.local.entity.AnalyticsLogEntity
import com.flashpool.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY title ASC")
    fun observeAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE category = :category ORDER BY title ASC")
    fun observeByCategory(category: String): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(products: List<ProductEntity>)

    @Query("SELECT COUNT(*) FROM products")
    suspend fun count(): Int
}

@Dao
interface AnalyticsDao {
    @Insert
    suspend fun insert(entity: AnalyticsLogEntity): Long

    @Query("SELECT * FROM analytics_log WHERE synced = 0 ORDER BY timestampEpochMillis ASC")
    suspend fun getUnsynced(): List<AnalyticsLogEntity>

    @Query("UPDATE analytics_log SET synced = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<Long>)
}
