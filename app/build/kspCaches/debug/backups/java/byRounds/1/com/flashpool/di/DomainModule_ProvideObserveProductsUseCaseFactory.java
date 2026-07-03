package com.flashpool.di;

import com.flashpool.domain.repository.ProductRepository;
import com.flashpool.domain.usecase.ObserveProductsUseCase;
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
public final class DomainModule_ProvideObserveProductsUseCaseFactory implements Factory<ObserveProductsUseCase> {
  private final Provider<ProductRepository> repoProvider;

  public DomainModule_ProvideObserveProductsUseCaseFactory(
      Provider<ProductRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ObserveProductsUseCase get() {
    return provideObserveProductsUseCase(repoProvider.get());
  }

  public static DomainModule_ProvideObserveProductsUseCaseFactory create(
      Provider<ProductRepository> repoProvider) {
    return new DomainModule_ProvideObserveProductsUseCaseFactory(repoProvider);
  }

  public static ObserveProductsUseCase provideObserveProductsUseCase(ProductRepository repo) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideObserveProductsUseCase(repo));
  }
}
