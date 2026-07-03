package com.flashpool.data.remote

import android.content.Context
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Serves catalog JSON from assets with a simulated network delay.
 * Point Retrofit at any host — this interceptor short-circuits /products.
 */
class FakeCatalogInterceptor(
    private val context: Context,
    private val json: Json,
    private val delayMillis: Long = 800L
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!request.url.encodedPath.endsWith("/products")) {
            return chain.proceed(request)
        }

        Thread.sleep(delayMillis)

        val body = context.assets.open("mock_products.json").bufferedReader().use { it.readText() }
        return Response.Builder()
            .code(200)
            .message("OK")
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(body.toResponseBody("application/json".toMediaType()))
            .build()
    }
}
