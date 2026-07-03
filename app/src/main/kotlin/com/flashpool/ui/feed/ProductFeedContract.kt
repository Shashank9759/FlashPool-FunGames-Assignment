package com.flashpool.ui.feed

import com.flashpool.domain.model.Product
import com.flashpool.domain.model.ProductCategory

data class ProductFeedUiState(
    val items: List<Product> = emptyList(),
    val selectedCategory: ProductCategory? = null,
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val flashDealEndEpochMillis: Long? = null,
    val flashDealRemainingMillis: Long? = null
)

sealed interface ProductFeedEvent {
    data class SelectCategory(val category: ProductCategory?) : ProductFeedEvent
    data object Refresh : ProductFeedEvent
    data object Retry : ProductFeedEvent
    data class JoinPool(val productId: String) : ProductFeedEvent
    data class ShareLink(val productId: String) : ProductFeedEvent
    data class CompleteCheckout(val productId: String) : ProductFeedEvent
}

sealed interface ProductFeedEffect {
    data class ShowMessage(val message: String) : ProductFeedEffect
}
