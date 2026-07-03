package com.flashpool.data.di;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.flashpool.domain.repository.ThemePreferenceRepository;
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
public final class DataModule_ProvideThemePreferenceRepositoryFactory implements Factory<ThemePreferenceRepository> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  public DataModule_ProvideThemePreferenceRepositoryFactory(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public ThemePreferenceRepository get() {
    return provideThemePreferenceRepository(dataStoreProvider.get());
  }

  public static DataModule_ProvideThemePreferenceRepositoryFactory create(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    return new DataModule_ProvideThemePreferenceRepositoryFactory(dataStoreProvider);
  }

  public static ThemePreferenceRepository provideThemePreferenceRepository(
      DataStore<Preferences> dataStore) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideThemePreferenceRepository(dataStore));
  }
}
