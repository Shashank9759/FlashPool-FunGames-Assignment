package com.flashpool.data.di;

import com.flashpool.data.firebase.FirebaseAnalyticsRemoteSync;
import com.flashpool.domain.repository.AnalyticsRemoteSync;
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
public final class FirebaseModule_ProvideAnalyticsRemoteSyncFactory implements Factory<AnalyticsRemoteSync> {
  private final Provider<FirebaseAnalyticsRemoteSync> implProvider;

  public FirebaseModule_ProvideAnalyticsRemoteSyncFactory(
      Provider<FirebaseAnalyticsRemoteSync> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public AnalyticsRemoteSync get() {
    return provideAnalyticsRemoteSync(implProvider.get());
  }

  public static FirebaseModule_ProvideAnalyticsRemoteSyncFactory create(
      Provider<FirebaseAnalyticsRemoteSync> implProvider) {
    return new FirebaseModule_ProvideAnalyticsRemoteSyncFactory(implProvider);
  }

  public static AnalyticsRemoteSync provideAnalyticsRemoteSync(FirebaseAnalyticsRemoteSync impl) {
    return Preconditions.checkNotNullFromProvides(FirebaseModule.INSTANCE.provideAnalyticsRemoteSync(impl));
  }
}
