package com.flashpool.data.mapper

import com.flashpool.data.local.entity.AnalyticsLogEntity
import com.flashpool.data.local.entity.ProductEntity
import com.flashpool.data.remote.dto.ProductDto
import com.flashpool.domain.model.InteractionEvent
import com.flashpool.domain.model.Product
import com.flashpool.domain.model.ProductCategory

fun ProductDto.toEntity(flashDealEndOverride: Long? = null): ProductEntity = ProductEntity(
    id = id,
    title = title,
    imageUrl = imageUrl,
    retailPrice = retailPrice,
    inventoryCount = inventoryCount,
    category = category,
    dealEndEpochMillis = flashDealEndOverride ?: dealEndEpochMillis
)

fun ProductEntity.toDomain(): Product = Product(
    id = id,
    title = title,
    imageUrl = imageUrl,
    retailPrice = retailPrice,
    inventoryCount = inventoryCount,
    category = ProductCategory.fromApiValue(category),
    dealEndEpochMillis = dealEndEpochMillis
)

fun InteractionEvent.toEntity(): AnalyticsLogEntity = AnalyticsLogEntity(
    eventType = when (this) {
        is InteractionEvent.JoinPool -> "JOIN_POOL"
        is InteractionEvent.ShareLink -> "SHARE_LINK"
        is InteractionEvent.CompletedCheckout -> "COMPLETED_CHECKOUT"
    },
    productId = productId,
    timestampEpochMillis = timestampEpochMillis
)
