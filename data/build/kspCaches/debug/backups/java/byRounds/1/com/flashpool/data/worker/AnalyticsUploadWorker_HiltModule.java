package com.flashpool.data.worker;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = AnalyticsUploadWorker.class
)
public interface AnalyticsUploadWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.flashpool.data.worker.AnalyticsUploadWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      AnalyticsUploadWorker_AssistedFactory factory);
}
