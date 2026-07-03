package com.flashpool.data.di;

import com.flashpool.data.local.dao.AnalyticsDao;
import com.flashpool.domain.repository.AnalyticsRemoteSync;
import com.flashpool.domain.repository.AnalyticsRepository;
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
public final class DataModule_ProvideAnalyticsRepositoryFactory implements Factory<AnalyticsRepository> {
  private final Provider<AnalyticsDao> analyticsDaoProvider;

  private final Provider<AnalyticsRemoteSync> remoteSyncProvider;

  public DataModule_ProvideAnalyticsRepositoryFactory(Provider<AnalyticsDao> analyticsDaoProvider,
      Provider<AnalyticsRemoteSync> remoteSyncProvider) {
    this.analyticsDaoProvider = analyticsDaoProvider;
    this.remoteSyncProvider = remoteSyncProvider;
  }

  @Override
  public AnalyticsRepository get() {
    return provideAnalyticsRepository(analyticsDaoProvider.get(), remoteSyncProvider.get());
  }

  public static DataModule_ProvideAnalyticsRepositoryFactory create(
      Provider<AnalyticsDao> analyticsDaoProvider,
      Provider<AnalyticsRemoteSync> remoteSyncProvider) {
    return new DataModule_ProvideAnalyticsRepositoryFactory(analyticsDaoProvider, remoteSyncProvider);
  }

  public static AnalyticsRepository provideAnalyticsRepository(AnalyticsDao analyticsDao,
      AnalyticsRemoteSync remoteSync) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideAnalyticsRepository(analyticsDao, remoteSync));
  }
}
