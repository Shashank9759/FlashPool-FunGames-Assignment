package com.flashpool.data.di;

import com.flashpool.data.remote.CatalogApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

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
public final class DataModule_ProvideCatalogApiFactory implements Factory<CatalogApi> {
  private final Provider<OkHttpClient> clientProvider;

  private final Provider<Json> jsonProvider;

  public DataModule_ProvideCatalogApiFactory(Provider<OkHttpClient> clientProvider,
      Provider<Json> jsonProvider) {
    this.clientProvider = clientProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public CatalogApi get() {
    return provideCatalogApi(clientProvider.get(), jsonProvider.get());
  }

  public static DataModule_ProvideCatalogApiFactory create(Provider<OkHttpClient> clientProvider,
      Provider<Json> jsonProvider) {
    return new DataModule_ProvideCatalogApiFactory(clientProvider, jsonProvider);
  }

  public static CatalogApi provideCatalogApi(OkHttpClient client, Json json) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideCatalogApi(client, json));
  }
}
