package com.flashpool.data.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.flashpool.domain.repository.AnalyticsRepository;
import dagger.internal.DaggerGenerated;
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
public final class AnalyticsUploadWorker_Factory {
  private final Provider<AnalyticsRepository> analyticsRepositoryProvider;

  public AnalyticsUploadWorker_Factory(Provider<AnalyticsRepository> analyticsRepositoryProvider) {
    this.analyticsRepositoryProvider = analyticsRepositoryProvider;
  }

  public AnalyticsUploadWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, analyticsRepositoryProvider.get());
  }

  public static AnalyticsUploadWorker_Factory create(
      Provider<AnalyticsRepository> analyticsRepositoryProvider) {
    return new AnalyticsUploadWorker_Factory(analyticsRepositoryProvider);
  }

  public static AnalyticsUploadWorker newInstance(Context context, WorkerParameters params,
      AnalyticsRepository analyticsRepository) {
    return new AnalyticsUploadWorker(context, params, analyticsRepository);
  }
}
