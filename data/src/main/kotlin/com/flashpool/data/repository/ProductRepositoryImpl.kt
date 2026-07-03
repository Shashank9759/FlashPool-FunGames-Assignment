package com.flashpool.data.repository

import com.flashpool.data.local.dao.ProductDao
import com.flashpool.data.mapper.toDomain
import com.flashpool.data.mapper.toEntity
import com.flashpool.data.remote.CatalogApi
import com.flashpool.domain.model.Product
import com.flashpool.domain.model.ProductCategory
import com.flashpool.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productDao: ProductDao,
    private val catalogApi: CatalogApi,
    private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override fun observeCatalog(category: ProductCategory?): Flow<List<Product>> {
        val source = if (category == null) {
            productDao.observeAll()
        } else {
            productDao.observeByCategory(category.name)
        }
        return source.map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun refreshCatalog(): Result<Unit> = withContext(ioDispatcher) {
        runCatching {
            val response = catalogApi.getProducts()
            val now = System.currentTimeMillis()
            val entities = response.products.map { dto ->
                val endMillis = if (dto.id == "flash-deal-1") {
                    now + FLASH_DEAL_DURATION_MS
                } else {
                    dto.dealEndEpochMillis
                }
                dto.toEntity(flashDealEndOverride = endMillis)
            }
            productDao.upsertAll(entities)
        }
    }

    companion object {
        const val FLASH_DEAL_DURATION_MS = 2 * 60 * 60 * 1000L // 2 hours from refresh
    }
}
