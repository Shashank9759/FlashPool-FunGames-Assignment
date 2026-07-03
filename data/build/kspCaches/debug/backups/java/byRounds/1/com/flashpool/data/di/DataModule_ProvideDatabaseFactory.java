package com.flashpool.data.di;

import android.content.Context;
import com.flashpool.data.local.FlashPoolDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DataModule_ProvideDatabaseFactory implements Factory<FlashPoolDatabase> {
  private final Provider<Context> contextProvider;

  public DataModule_ProvideDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public FlashPoolDatabase get() {
    return provideDatabase(contextProvider.get());
  }

  public static DataModule_ProvideDatabaseFactory create(Provider<Context> contextProvider) {
    return new DataModule_ProvideDatabaseFactory(contextProvider);
  }

  public static FlashPoolDatabase provideDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideDatabase(context));
  }
}
