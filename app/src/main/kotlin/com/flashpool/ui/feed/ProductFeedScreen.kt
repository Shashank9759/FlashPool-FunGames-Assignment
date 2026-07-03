package com.flashpool.ui.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flashpool.domain.model.AppTheme
import com.flashpool.ui.components.CategoryFilterRow
import com.flashpool.ui.components.ErrorBanner
import com.flashpool.ui.components.FlashPoolTopBar
import com.flashpool.ui.components.ProductCard
import com.flashpool.ui.components.ProductListShimmer
import com.flashpool.ui.components.TicketStubFeaturedCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFeedScreen(
    appTheme: AppTheme,
    onThemeClick: () -> Unit,
    viewModel: ProductFeedViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ProductFeedEffect.ShowMessage -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            FlashPoolTopBar(appTheme = appTheme, onThemeClick = onThemeClick)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CategoryFilterRow(
                selected = state.selectedCategory,
                onSelect = { viewModel.onEvent(ProductFeedEvent.SelectCategory(it)) }
            )

            state.error?.let { msg ->
                ErrorBanner(message = msg, onRetry = { viewModel.onEvent(ProductFeedEvent.Retry) })
            }

            when {
                state.isLoading && state.items.isEmpty() -> ProductListShimmer()
                else -> {
                    val isHome = state.selectedCategory == null
                    val flashDeal = if (isHome) {
                        state.items.firstOrNull { it.dealEndEpochMillis != null }
                    } else {
                        null
                    }

                    key(state.selectedCategory) {
                        val listState = rememberLazyListState()
                        val itemIds = state.items.map { it.id }

                        LaunchedEffect(state.selectedCategory, itemIds) {
                            listState.scrollToItem(0)
                        }

                        PullToRefreshBox(
                            isRefreshing = state.isRefreshing,
                            onRefresh = { viewModel.onEvent(ProductFeedEvent.Refresh) },
                            modifier = Modifier.fillMaxSize()
                        ) {
                            LazyColumn(
                                state = listState,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                if (flashDeal != null && state.flashDealRemainingMillis != null) {
                                    item(key = "flash_deal") {
                                        Spacer(Modifier.height(4.dp))
                                        TicketStubFeaturedCard(
                                            product = flashDeal,
                                            remainingMillis = state.flashDealRemainingMillis,
                                            onJoinPool = {
                                                viewModel.onEvent(ProductFeedEvent.JoinPool(flashDeal.id))
                                            }
                                        )
                                        Spacer(Modifier.height(8.dp))
                                    }
                                }

                                items(state.items, key = { it.id }) { product ->
                                    if (product.dealEndEpochMillis == null) {
                                        ProductCard(
                                            product = product,
                                            onShare = {
                                                viewModel.onEvent(ProductFeedEvent.ShareLink(product.id))
                                            },
                                            onCheckout = {
                                                viewModel.onEvent(ProductFeedEvent.CompleteCheckout(product.id))
                                            },
                                            enabled = product.inventoryCount > 0
                                        )
                                    }
                                }

                                if (state.isLoading || state.isRefreshing) {
                                    item(key = "shimmer_tail") {
                                        ProductListShimmer(itemCount = 1)
                                    }
                                }

                                if (state.items.isEmpty() && !state.isLoading) {
                                    item {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(32.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                "No products in this category",
                                                style = MaterialTheme.typography.bodyLarge,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }

                                item { Spacer(Modifier.height(8.dp)) }
                            }
                        }
                    }
                }
            }
        }
    }
}
