package com.flashpool.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: String,
    val title: String,
    val imageUrl: String,
    val retailPrice: Double,
    val inventoryCount: Int,
    val category: String,
    val dealEndEpochMillis: Long? = null
)

@Serializable
data class ProductsResponse(
    val products: List<ProductDto>
)

@Serializable
data class AnalyticsBatchRequest(
    val events: List<AnalyticsEventDto>
)

@Serializable
data class AnalyticsEventDto(
    val eventType: String,
    val productId: String,
    val timestampEpochMillis: Long
)
