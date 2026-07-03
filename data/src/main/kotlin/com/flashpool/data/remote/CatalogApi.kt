package com.flashpool.data.remote

import com.flashpool.data.remote.dto.ProductsResponse
import retrofit2.http.GET

// TODO: swap fake interceptor for real BASE_URL once backend is live
interface CatalogApi {
    @GET("products")
    suspend fun getProducts(): ProductsResponse
}
