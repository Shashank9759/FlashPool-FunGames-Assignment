package com.flashpool.ui.feed;

import androidx.lifecycle.SavedStateHandle;
import com.flashpool.domain.usecase.LogInteractionUseCase;
import com.flashpool.domain.usecase.ObserveProductsUseCase;
import com.flashpool.domain.usecase.RefreshCatalogUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class ProductFeedViewModel_Factory implements Factory<ProductFeedViewModel> {
  private final Provider<ObserveProductsUseCase> observeProductsProvider;

  private final Provider<RefreshCatalogUseCase> refreshCatalogProvider;

  private final Provider<LogInteractionUseCase> logInteractionProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public ProductFeedViewModel_Factory(Provider<ObserveProductsUseCase> observeProductsProvider,
      Provider<RefreshCatalogUseCase> refreshCatalogProvider,
      Provider<LogInteractionUseCase> logInteractionProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.observeProductsProvider = observeProductsProvider;
    this.refreshCatalogProvider = refreshCatalogProvider;
    this.logInteractionProvider = logInteractionProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ProductFeedViewModel get() {
    return newInstance(observeProductsProvider.get(), refreshCatalogProvider.get(), logInteractionProvider.get(), savedStateHandleProvider.get());
  }

  public static ProductFeedViewModel_Factory create(
      Provider<ObserveProductsUseCase> observeProductsProvider,
      Provider<RefreshCatalogUseCase> refreshCatalogProvider,
      Provider<LogInteractionUseCase> logInteractionProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ProductFeedViewModel_Factory(observeProductsProvider, refreshCatalogProvider, logInteractionProvider, savedStateHandleProvider);
  }

  public static ProductFeedViewModel newInstance(ObserveProductsUseCase observeProducts,
      RefreshCatalogUseCase refreshCatalog, LogInteractionUseCase logInteraction,
      SavedStateHandle savedStateHandle) {
    return new ProductFeedViewModel(observeProducts, refreshCatalog, logInteraction, savedStateHandle);
  }
}
