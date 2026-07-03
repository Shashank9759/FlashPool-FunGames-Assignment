package com.flashpool.domain.repository

import com.flashpool.domain.model.InteractionEvent
import com.flashpool.domain.model.Product
import com.flashpool.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun observeCatalog(category: ProductCategory?): Flow<List<Product>>
    suspend fun refreshCatalog(): Result<Unit>
}

interface AnalyticsRepository {
    suspend fun logEvent(event: InteractionEvent)
    suspend fun uploadPendingEvents(): Result<Int>
}
