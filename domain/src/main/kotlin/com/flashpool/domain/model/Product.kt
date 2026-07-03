package com.flashpool.domain.model

enum class ProductCategory(val displayName: String) {
    FMCG("FMCG"),
    LIFESTYLE("Lifestyle"),
    TECH("Tech");

    companion object {
        fun fromApiValue(value: String): ProductCategory = when (value.uppercase()) {
            "FMCG" -> FMCG
            "LIFESTYLE" -> LIFESTYLE
            "TECH" -> TECH
            else -> FMCG
        }
    }
}

data class Product(
    val id: String,
    val title: String,
    val imageUrl: String,
    val retailPrice: Double,
    val inventoryCount: Int,
    val category: ProductCategory,
    val dealEndEpochMillis: Long? = null
)
