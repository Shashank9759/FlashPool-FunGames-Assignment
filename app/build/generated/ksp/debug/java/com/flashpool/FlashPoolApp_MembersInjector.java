package com.flashpool;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class FlashPoolApp_MembersInjector implements MembersInjector<FlashPoolApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public FlashPoolApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<FlashPoolApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new FlashPoolApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(FlashPoolApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.flashpool.FlashPoolApp.workerFactory")
  public static void injectWorkerFactory(FlashPoolApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
