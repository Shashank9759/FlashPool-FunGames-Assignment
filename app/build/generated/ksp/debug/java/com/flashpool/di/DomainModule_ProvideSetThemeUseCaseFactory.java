package com.flashpool.di;

import com.flashpool.domain.repository.ThemePreferenceRepository;
import com.flashpool.domain.usecase.SetThemePreferenceUseCase;
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
public final class DomainModule_ProvideSetThemeUseCaseFactory implements Factory<SetThemePreferenceUseCase> {
  private final Provider<ThemePreferenceRepository> repoProvider;

  public DomainModule_ProvideSetThemeUseCaseFactory(
      Provider<ThemePreferenceRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public SetThemePreferenceUseCase get() {
    return provideSetThemeUseCase(repoProvider.get());
  }

  public static DomainModule_ProvideSetThemeUseCaseFactory create(
      Provider<ThemePreferenceRepository> repoProvider) {
    return new DomainModule_ProvideSetThemeUseCaseFactory(repoProvider);
  }

  public static SetThemePreferenceUseCase provideSetThemeUseCase(ThemePreferenceRepository repo) {
    return Preconditions.checkNotNullFromProvides(DomainModule.INSTANCE.provideSetThemeUseCase(repo));
  }
}
