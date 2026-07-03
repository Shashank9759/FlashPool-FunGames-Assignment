package com.flashpool.data.di;

import com.flashpool.data.local.FlashPoolDatabase;
import com.flashpool.data.local.dao.AnalyticsDao;
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
public final class DataModule_ProvideAnalyticsDaoFactory implements Factory<AnalyticsDao> {
  private final Provider<FlashPoolDatabase> dbProvider;

  public DataModule_ProvideAnalyticsDaoFactory(Provider<FlashPoolDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public AnalyticsDao get() {
    return provideAnalyticsDao(dbProvider.get());
  }

  public static DataModule_ProvideAnalyticsDaoFactory create(
      Provider<FlashPoolDatabase> dbProvider) {
    return new DataModule_ProvideAnalyticsDaoFactory(dbProvider);
  }

  public static AnalyticsDao provideAnalyticsDao(FlashPoolDatabase db) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideAnalyticsDao(db));
  }
}
