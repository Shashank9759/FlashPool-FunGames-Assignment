package com.flashpool.data.di;

import android.content.Context;
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
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<Context> contextProvider;

  private final Provider<Json> jsonProvider;

  public DataModule_ProvideOkHttpClientFactory(Provider<Context> contextProvider,
      Provider<Json> jsonProvider) {
    this.contextProvider = contextProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(contextProvider.get(), jsonProvider.get());
  }

  public static DataModule_ProvideOkHttpClientFactory create(Provider<Context> contextProvider,
      Provider<Json> jsonProvider) {
    return new DataModule_ProvideOkHttpClientFactory(contextProvider, jsonProvider);
  }

  public static OkHttpClient provideOkHttpClient(Context context, Json json) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideOkHttpClient(context, json));
  }
}
