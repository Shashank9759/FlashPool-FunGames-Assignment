package com.flashpool.domain.usecase

import com.flashpool.domain.model.InteractionEvent
import com.flashpool.domain.model.Product
import com.flashpool.domain.model.ProductCategory
import com.flashpool.domain.repository.AnalyticsRepository
import com.flashpool.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay

class ObserveProductsUseCase(
    private val productRepository: ProductRepository
) {
    operator fun invoke(category: ProductCategory?): Flow<List<Product>> =
        productRepository.observeCatalog(category)
}

class RefreshCatalogUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Result<Unit> = productRepository.refreshCatalog()
}

class LogInteractionUseCase(
    private val analyticsRepository: AnalyticsRepository
) {
    suspend operator fun invoke(event: InteractionEvent) {
        analyticsRepository.logEvent(event)
    }
}

/** Computes remaining millis from an absolute end-instant each tick — self-corrects drift. */
class CountdownTicker(
    private val endEpochMillis: Long,
    private val nowMillis: () -> Long = { System.currentTimeMillis() }
) {
    fun remainingMillisFlow(): Flow<Long> = flow {
        while (true) {
            val remaining = (endEpochMillis - nowMillis()).coerceAtLeast(0L)
            emit(remaining)
            if (remaining == 0L) break
            delay(1000)
        }
    }
}
