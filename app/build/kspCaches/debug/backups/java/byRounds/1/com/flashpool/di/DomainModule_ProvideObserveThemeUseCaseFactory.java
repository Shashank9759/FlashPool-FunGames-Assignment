package com.flashpool.di;

import com.flashpool.domain.repository.ThemePreferenceRepository;
import com.flashpool.domain.usecase.ObserveThemePreferenceUseCase;
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
public final class DomainModule_ProvideObserveThemeUseCaseFactory implements Factory<ObserveThemePreferenceUseCase> {
  private final Provider<ThemePreferenceRepository> repoProvider;

  public DomainModule_ProvideObserveThemeUseCaseFactory(
      Provider<ThemePreferenceRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ObserveThemePreferenceUseCase get() {
    return provideObserveThemeUseCase(repoProvider.get());
  }

  public static DomainModule_ProvideObserveThemeUseCaseFactory create(
      Provider<ThemePreferenceRepository> repoProvider) {
    return new DomainModule_ProvideObserveThemeUseCaseFactory(repoProvider);
  }

  public static ObserveThemePreferenceUseCase provideObserveThemeUseCase(
      ThemePreferenceRepository repo) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideObserveThemeUseCase(repo));
  }
}
