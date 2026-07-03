package com.flashpool.ui.feed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flashpool.domain.model.InteractionEvent
import com.flashpool.domain.model.ProductCategory
import com.flashpool.domain.usecase.CountdownTicker
import com.flashpool.domain.usecase.LogInteractionUseCase
import com.flashpool.domain.usecase.ObserveProductsUseCase
import com.flashpool.domain.usecase.RefreshCatalogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProductFeedViewModel @Inject constructor(
    private val observeProducts: ObserveProductsUseCase,
    private val refreshCatalog: RefreshCatalogUseCase,
    private val logInteraction: LogInteractionUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductFeedUiState())
    val uiState: StateFlow<ProductFeedUiState> = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<ProductFeedEffect>()
    val effects: SharedFlow<ProductFeedEffect> = _effects.asSharedFlow()

    private val selectedCategory = MutableStateFlow<ProductCategory?>(null)
    private var countdownJob: Job? = null

    // SavedStateHandle keeps the deal end-instant across process death so the countdown can resume
    private var flashDealEndMillis: Long?
        get() = savedStateHandle[KEY_DEAL_END]
        set(value) {
            savedStateHandle[KEY_DEAL_END] = value
        }

    init {
        savedStateHandle.get<Long>(KEY_DEAL_END)?.let { startCountdown(it) }

        viewModelScope.launch {
            selectedCategory
                .flatMapLatest { category -> observeProducts(category) }
                .collect { products ->
                    val dealProduct = products.firstOrNull { it.dealEndEpochMillis != null }
                    val endMillis = dealProduct?.dealEndEpochMillis
                    if (endMillis != null && endMillis != flashDealEndMillis) {
                        flashDealEndMillis = endMillis
                        startCountdown(endMillis)
                    }
                    _uiState.update {
                        it.copy(
                            items = products,
                            isLoading = false,
                            flashDealEndEpochMillis = endMillis
                        )
                    }
                }
        }

        onEvent(ProductFeedEvent.Refresh)
    }

    fun onEvent(event: ProductFeedEvent) {
        when (event) {
            is ProductFeedEvent.SelectCategory -> {
                _uiState.update {
                    it.copy(
                        selectedCategory = event.category,
                        isLoading = it.items.isEmpty()
                    )
                }
                selectedCategory.value = event.category
            }
            ProductFeedEvent.Refresh, ProductFeedEvent.Retry -> refresh()
            is ProductFeedEvent.JoinPool -> track(
                InteractionEvent.JoinPool(event.productId, System.currentTimeMillis())
            )
            is ProductFeedEvent.ShareLink -> track(
                InteractionEvent.ShareLink(event.productId, System.currentTimeMillis())
            )
            is ProductFeedEvent.CompleteCheckout -> track(
                InteractionEvent.CompletedCheckout(event.productId, System.currentTimeMillis())
            )
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            val hasCache = _uiState.value.items.isNotEmpty()
            _uiState.update {
                it.copy(
                    isRefreshing = hasCache,
                    isLoading = !hasCache,
                    error = null
                )
            }
            refreshCatalog()
                .onSuccess {
                    _uiState.update { it.copy(isRefreshing = false, isLoading = false, error = null) }
                }
                .onFailure { e ->
                    _uiState.update {
                        it.copy(
                            isRefreshing = false,
                            isLoading = false,
                            error = e.message ?: "Could not refresh catalog"
                        )
                    }
                }
        }
    }

    private fun startCountdown(endEpochMillis: Long) {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            CountdownTicker(endEpochMillis).remainingMillisFlow().collect { remaining ->
                _uiState.update { it.copy(flashDealRemainingMillis = remaining) }
            }
        }
    }

    private fun track(event: InteractionEvent) {
        viewModelScope.launch {
            logInteraction(event)
            val label = when (event) {
                is InteractionEvent.JoinPool -> "Joined pool"
                is InteractionEvent.ShareLink -> "Link shared"
                is InteractionEvent.CompletedCheckout -> "Checkout logged"
            }
            _effects.emit(ProductFeedEffect.ShowMessage(label))
        }
    }

    companion object {
        private const val KEY_DEAL_END = "flash_deal_end_epoch"
    }
}
