package com.flashpool.ui.feed

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.flashpool.domain.model.Product
import com.flashpool.domain.model.ProductCategory
import com.flashpool.domain.model.InteractionEvent
import com.flashpool.domain.repository.AnalyticsRepository
import com.flashpool.domain.repository.ProductRepository
import com.flashpool.domain.usecase.LogInteractionUseCase
import com.flashpool.domain.usecase.ObserveProductsUseCase
import com.flashpool.domain.usecase.RefreshCatalogUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductFeedViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var productRepository: ProductRepository
    private lateinit var analyticsRepository: AnalyticsRepository
    private val catalogFlow = MutableStateFlow(sampleProducts())

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        productRepository = mockk(relaxed = true)
        analyticsRepository = mockk(relaxed = true)

        every { productRepository.observeCatalog(any()) } answers {
            val category = firstArg<ProductCategory?>()
            if (category == null) catalogFlow else flowOf(
                catalogFlow.value.filter { it.category == category }
            )
        }
        coEvery { productRepository.refreshCatalog() } returns Result.success(Unit)
        coEvery { analyticsRepository.logEvent(any()) } returns Unit
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel(): ProductFeedViewModel {
        val observe = ObserveProductsUseCase(productRepository)
        val refresh = RefreshCatalogUseCase(productRepository)
        val log = LogInteractionUseCase(analyticsRepository)
        return ProductFeedViewModel(observe, refresh, log, SavedStateHandle())
    }

    @Test
    fun `refresh success clears error and loading`() = runTest {
        val vm = createViewModel()
        advanceUntilIdle()

        vm.uiState.test {
            val loaded = awaitItem()
            assertFalse(loaded.isLoading)
            assertNull(loaded.error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `refresh failure keeps items and sets error`() = runTest {
        coEvery { productRepository.refreshCatalog() } returns Result.failure(RuntimeException("offline"))
        val vm = createViewModel()
        advanceUntilIdle()

        vm.onEvent(ProductFeedEvent.Refresh)
        advanceUntilIdle()

        val state = vm.uiState.value
        assertEquals("offline", state.error)
        assertFalse(state.items.isEmpty())
    }

    @Test
    fun `join pool logs analytics event`() = runTest {
        val vm = createViewModel()
        advanceUntilIdle()

        vm.onEvent(ProductFeedEvent.JoinPool("p1"))
        advanceUntilIdle()

        coVerify {
            analyticsRepository.logEvent(
                match { it is InteractionEvent.JoinPool && it.productId == "p1" }
            )
        }
    }

    private fun sampleProducts() = listOf(
        Product("p1", "Widget", "url", 19.99, 12, ProductCategory.TECH, null),
        Product("p2", "Snack Pack", "url", 3.99, 40, ProductCategory.FMCG, null),
        Product("p3", "Leather Wallet", "url", 34.0, 8, ProductCategory.LIFESTYLE, null)
    )
}
