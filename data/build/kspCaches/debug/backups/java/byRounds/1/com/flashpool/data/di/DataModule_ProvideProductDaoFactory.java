package com.flashpool.data.di;

import com.flashpool.data.local.FlashPoolDatabase;
import com.flashpool.data.local.dao.ProductDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DataModule_ProvideProductDaoFactory implements Factory<ProductDao> {
  private final Provider<FlashPoolDatabase> dbProvider;

  public DataModule_ProvideProductDaoFactory(Provider<FlashPoolDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ProductDao get() {
    return provideProductDao(dbProvider.get());
  }

  public static DataModule_ProvideProductDaoFactory create(Provider<FlashPoolDatabase> dbProvider) {
    return new DataModule_ProvideProductDaoFactory(dbProvider);
  }

  public static ProductDao provideProductDao(FlashPoolDatabase db) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideProductDao(db));
  }
}
