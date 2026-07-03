package com.flashpool.di;

import com.flashpool.domain.repository.AnalyticsRepository;
import com.flashpool.domain.usecase.LogInteractionUseCase;
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
public final class DomainModule_ProvideLogInteractionUseCaseFactory implements Factory<LogInteractionUseCase> {
  private final Provider<AnalyticsRepository> repoProvider;

  public DomainModule_ProvideLogInteractionUseCaseFactory(
      Provider<AnalyticsRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public LogInteractionUseCase get() {
    return provideLogInteractionUseCase(repoProvider.get());
  }

  public static DomainModule_ProvideLogInteractionUseCaseFactory create(
      Provider<AnalyticsRepository> repoProvider) {
    return new DomainModule_ProvideLogInteractionUseCaseFactory(repoProvider);
  }

  public static LogInteractionUseCase provideLogInteractionUseCase(AnalyticsRepository repo) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideLogInteractionUseCase(repo));
  }
}
