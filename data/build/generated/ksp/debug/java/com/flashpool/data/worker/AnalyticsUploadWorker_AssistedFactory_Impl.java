package com.flashpool.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class AnalyticsUploadWorker_AssistedFactory_Impl implements AnalyticsUploadWorker_AssistedFactory {
  private final AnalyticsUploadWorker_Factory delegateFactory;

  AnalyticsUploadWorker_AssistedFactory_Impl(AnalyticsUploadWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public AnalyticsUploadWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<AnalyticsUploadWorker_AssistedFactory> create(
      AnalyticsUploadWorker_Factory delegateFactory) {
    return InstanceFactory.create(new AnalyticsUploadWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<AnalyticsUploadWorker_AssistedFactory> createFactoryProvider(
      AnalyticsUploadWorker_Factory delegateFactory) {
    return InstanceFactory.create(new AnalyticsUploadWorker_AssistedFactory_Impl(delegateFactory));
  }
}
