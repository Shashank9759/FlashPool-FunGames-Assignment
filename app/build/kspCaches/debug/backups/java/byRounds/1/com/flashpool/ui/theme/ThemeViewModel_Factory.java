package com.flashpool.ui.theme;

import com.flashpool.domain.usecase.ObserveThemePreferenceUseCase;
import com.flashpool.domain.usecase.SetThemePreferenceUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ThemeViewModel_Factory implements Factory<ThemeViewModel> {
  private final Provider<ObserveThemePreferenceUseCase> observeThemePreferenceProvider;

  private final Provider<SetThemePreferenceUseCase> setThemePreferenceProvider;

  public ThemeViewModel_Factory(
      Provider<ObserveThemePreferenceUseCase> observeThemePreferenceProvider,
      Provider<SetThemePreferenceUseCase> setThemePreferenceProvider) {
    this.observeThemePreferenceProvider = observeThemePreferenceProvider;
    this.setThemePreferenceProvider = setThemePreferenceProvider;
  }

  @Override
  public ThemeViewModel get() {
    return newInstance(observeThemePreferenceProvider.get(), setThemePreferenceProvider.get());
  }

  public static ThemeViewModel_Factory create(
      Provider<ObserveThemePreferenceUseCase> observeThemePreferenceProvider,
      Provider<SetThemePreferenceUseCase> setThemePreferenceProvider) {
    return new ThemeViewModel_Factory(observeThemePreferenceProvider, setThemePreferenceProvider);
  }

  public static ThemeViewModel newInstance(ObserveThemePreferenceUseCase observeThemePreference,
      SetThemePreferenceUseCase setThemePreference) {
    return new ThemeViewModel(observeThemePreference, setThemePreference);
  }
}
