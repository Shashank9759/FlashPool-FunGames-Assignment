package com.flashpool.data.firebase;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class FirebaseAnalyticsRemoteSync_Factory implements Factory<FirebaseAnalyticsRemoteSync> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public FirebaseAnalyticsRemoteSync_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FirebaseAnalyticsRemoteSync get() {
    return newInstance(firestoreProvider.get());
  }

  public static FirebaseAnalyticsRemoteSync_Factory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new FirebaseAnalyticsRemoteSync_Factory(firestoreProvider);
  }

  public static FirebaseAnalyticsRemoteSync newInstance(FirebaseFirestore firestore) {
    return new FirebaseAnalyticsRemoteSync(firestore);
  }
}
