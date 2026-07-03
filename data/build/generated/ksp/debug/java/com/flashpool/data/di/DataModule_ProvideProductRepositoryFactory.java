package com.flashpool.data.di;

import com.flashpool.data.local.dao.ProductDao;
import com.flashpool.data.remote.CatalogApi;
import com.flashpool.domain.repository.ProductRepository;
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
public final class DataModule_ProvideProductRepositoryFactory implements Factory<ProductRepository> {
  private final Provider<ProductDao> productDaoProvider;

  private final Provider<CatalogApi> catalogApiProvider;

  public DataModule_ProvideProductRepositoryFactory(Provider<ProductDao> productDaoProvider,
      Provider<CatalogApi> catalogApiProvider) {
    this.productDaoProvider = productDaoProvider;
    this.catalogApiProvider = catalogApiProvider;
  }

  @Override
  public ProductRepository get() {
    return provideProductRepository(productDaoProvider.get(), catalogApiProvider.get());
  }

  public static DataModule_ProvideProductRepositoryFactory create(
      Provider<ProductDao> productDaoProvider, Provider<CatalogApi> catalogApiProvider) {
    return new DataModule_ProvideProductRepositoryFactory(productDaoProvider, catalogApiProvider);
  }

  public static ProductRepository provideProductRepository(ProductDao productDao,
      CatalogApi catalogApi) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideProductRepository(productDao, catalogApi));
  }
}
