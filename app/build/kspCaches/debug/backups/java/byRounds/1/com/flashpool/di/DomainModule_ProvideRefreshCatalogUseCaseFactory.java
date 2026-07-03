package com.flashpool.di;

import com.flashpool.domain.repository.ProductRepository;
import com.flashpool.domain.usecase.RefreshCatalogUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DomainModule_ProvideRefreshCatalogUseCaseFactory implements Factory<RefreshCatalogUseCase> {
  private final Provider<ProductRepository> repoProvider;

  public DomainModule_ProvideRefreshCatalogUseCaseFactory(
      Provider<ProductRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public RefreshCatalogUseCase get() {
    return provideRefreshCatalogUseCase(repoProvider.get());
  }

  public static DomainModule_ProvideRefreshCatalogUseCaseFactory create(
      Provider<ProductRepository> repoProvider) {
    return new DomainModule_ProvideRefreshCatalogUseCaseFactory(repoProvider);
  }

  public static RefreshCatalogUseCase provideRefreshCatalogUseCase(ProductRepository repo) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideRefreshCatalogUseCase(repo));
  }
}
