package com.flashpool;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.flashpool.data.di.DataModule_ProvideAnalyticsDaoFactory;
import com.flashpool.data.di.DataModule_ProvideAnalyticsRepositoryFactory;
import com.flashpool.data.di.DataModule_ProvideCatalogApiFactory;
import com.flashpool.data.di.DataModule_ProvideDatabaseFactory;
import com.flashpool.data.di.DataModule_ProvideJsonFactory;
import com.flashpool.data.di.DataModule_ProvideOkHttpClientFactory;
import com.flashpool.data.di.DataModule_ProvideProductDaoFactory;
import com.flashpool.data.di.DataModule_ProvideProductRepositoryFactory;
import com.flashpool.data.di.DataModule_ProvideThemeDataStoreFactory;
import com.flashpool.data.di.DataModule_ProvideThemePreferenceRepositoryFactory;
import com.flashpool.data.di.FirebaseModule_ProvideAnalyticsRemoteSyncFactory;
import com.flashpool.data.di.FirebaseModule_ProvideFirestoreFactory;
import com.flashpool.data.firebase.FirebaseAnalyticsRemoteSync;
import com.flashpool.data.local.FlashPoolDatabase;
import com.flashpool.data.local.dao.AnalyticsDao;
import com.flashpool.data.local.dao.ProductDao;
import com.flashpool.data.remote.CatalogApi;
import com.flashpool.data.worker.AnalyticsUploadWorker;
import com.flashpool.data.worker.AnalyticsUploadWorker_AssistedFactory;
import com.flashpool.di.DomainModule_ProvideLogInteractionUseCaseFactory;
import com.flashpool.di.DomainModule_ProvideObserveProductsUseCaseFactory;
import com.flashpool.di.DomainModule_ProvideObserveThemeUseCaseFactory;
import com.flashpool.di.DomainModule_ProvideRefreshCatalogUseCaseFactory;
import com.flashpool.di.DomainModule_ProvideSetThemeUseCaseFactory;
import com.flashpool.domain.repository.AnalyticsRemoteSync;
import com.flashpool.domain.repository.AnalyticsRepository;
import com.flashpool.domain.repository.ProductRepository;
import com.flashpool.domain.repository.ThemePreferenceRepository;
import com.flashpool.domain.usecase.LogInteractionUseCase;
import com.flashpool.domain.usecase.ObserveProductsUseCase;
import com.flashpool.domain.usecase.ObserveThemePreferenceUseCase;
import com.flashpool.domain.usecase.RefreshCatalogUseCase;
import com.flashpool.domain.usecase.SetThemePreferenceUseCase;
import com.flashpool.ui.feed.ProductFeedViewModel;
import com.flashpool.ui.feed.ProductFeedViewModel_HiltModules;
import com.flashpool.ui.theme.ThemeViewModel;
import com.flashpool.ui.theme.ThemeViewModel_HiltModules;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.firestore.FirebaseFirestore;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

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
public final class DaggerFlashPoolApp_HiltComponents_SingletonC {
  private DaggerFlashPoolApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public FlashPoolApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements FlashPoolApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public FlashPoolApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements FlashPoolApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public FlashPoolApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements FlashPoolApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public FlashPoolApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements FlashPoolApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FlashPoolApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements FlashPoolApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public FlashPoolApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements FlashPoolApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public FlashPoolApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements FlashPoolApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public FlashPoolApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends FlashPoolApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends FlashPoolApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends FlashPoolApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends FlashPoolApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>of(LazyClassKeyProvider.com_flashpool_ui_feed_ProductFeedViewModel, ProductFeedViewModel_HiltModules.KeyModule.provide(), LazyClassKeyProvider.com_flashpool_ui_theme_ThemeViewModel, ThemeViewModel_HiltModules.KeyModule.provide()));
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_flashpool_ui_theme_ThemeViewModel = "com.flashpool.ui.theme.ThemeViewModel";

      static String com_flashpool_ui_feed_ProductFeedViewModel = "com.flashpool.ui.feed.ProductFeedViewModel";

      @KeepFieldType
      ThemeViewModel com_flashpool_ui_theme_ThemeViewModel2;

      @KeepFieldType
      ProductFeedViewModel com_flashpool_ui_feed_ProductFeedViewModel2;
    }
  }

  private static final class ViewModelCImpl extends FlashPoolApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<ProductFeedViewModel> productFeedViewModelProvider;

    private Provider<ThemeViewModel> themeViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.productFeedViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.themeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>of(LazyClassKeyProvider.com_flashpool_ui_feed_ProductFeedViewModel, ((Provider) productFeedViewModelProvider), LazyClassKeyProvider.com_flashpool_ui_theme_ThemeViewModel, ((Provider) themeViewModelProvider)));
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_flashpool_ui_feed_ProductFeedViewModel = "com.flashpool.ui.feed.ProductFeedViewModel";

      static String com_flashpool_ui_theme_ThemeViewModel = "com.flashpool.ui.theme.ThemeViewModel";

      @KeepFieldType
      ProductFeedViewModel com_flashpool_ui_feed_ProductFeedViewModel2;

      @KeepFieldType
      ThemeViewModel com_flashpool_ui_theme_ThemeViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.flashpool.ui.feed.ProductFeedViewModel 
          return (T) new ProductFeedViewModel(singletonCImpl.provideObserveProductsUseCaseProvider.get(), singletonCImpl.provideRefreshCatalogUseCaseProvider.get(), singletonCImpl.provideLogInteractionUseCaseProvider.get(), viewModelCImpl.savedStateHandle);

          case 1: // com.flashpool.ui.theme.ThemeViewModel 
          return (T) new ThemeViewModel(singletonCImpl.provideObserveThemeUseCaseProvider.get(), singletonCImpl.provideSetThemeUseCaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends FlashPoolApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends FlashPoolApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends FlashPoolApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<FlashPoolDatabase> provideDatabaseProvider;

    private Provider<FirebaseFirestore> provideFirestoreProvider;

    private Provider<FirebaseAnalyticsRemoteSync> firebaseAnalyticsRemoteSyncProvider;

    private Provider<AnalyticsRemoteSync> provideAnalyticsRemoteSyncProvider;

    private Provider<AnalyticsRepository> provideAnalyticsRepositoryProvider;

    private Provider<AnalyticsUploadWorker_AssistedFactory> analyticsUploadWorker_AssistedFactoryProvider;

    private Provider<Json> provideJsonProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<CatalogApi> provideCatalogApiProvider;

    private Provider<ProductRepository> provideProductRepositoryProvider;

    private Provider<ObserveProductsUseCase> provideObserveProductsUseCaseProvider;

    private Provider<RefreshCatalogUseCase> provideRefreshCatalogUseCaseProvider;

    private Provider<LogInteractionUseCase> provideLogInteractionUseCaseProvider;

    private Provider<DataStore<Preferences>> provideThemeDataStoreProvider;

    private Provider<ThemePreferenceRepository> provideThemePreferenceRepositoryProvider;

    private Provider<ObserveThemePreferenceUseCase> provideObserveThemeUseCaseProvider;

    private Provider<SetThemePreferenceUseCase> provideSetThemeUseCaseProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private AnalyticsDao analyticsDao() {
      return DataModule_ProvideAnalyticsDaoFactory.provideAnalyticsDao(provideDatabaseProvider.get());
    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return ImmutableMap.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>of("com.flashpool.data.worker.AnalyticsUploadWorker", ((Provider) analyticsUploadWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private ProductDao productDao() {
      return DataModule_ProvideProductDaoFactory.provideProductDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<FlashPoolDatabase>(singletonCImpl, 2));
      this.provideFirestoreProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseFirestore>(singletonCImpl, 5));
      this.firebaseAnalyticsRemoteSyncProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseAnalyticsRemoteSync>(singletonCImpl, 4));
      this.provideAnalyticsRemoteSyncProvider = DoubleCheck.provider(new SwitchingProvider<AnalyticsRemoteSync>(singletonCImpl, 3));
      this.provideAnalyticsRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AnalyticsRepository>(singletonCImpl, 1));
      this.analyticsUploadWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<AnalyticsUploadWorker_AssistedFactory>(singletonCImpl, 0));
      this.provideJsonProvider = DoubleCheck.provider(new SwitchingProvider<Json>(singletonCImpl, 10));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 9));
      this.provideCatalogApiProvider = DoubleCheck.provider(new SwitchingProvider<CatalogApi>(singletonCImpl, 8));
      this.provideProductRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ProductRepository>(singletonCImpl, 7));
      this.provideObserveProductsUseCaseProvider = DoubleCheck.provider(new SwitchingProvider<ObserveProductsUseCase>(singletonCImpl, 6));
      this.provideRefreshCatalogUseCaseProvider = DoubleCheck.provider(new SwitchingProvider<RefreshCatalogUseCase>(singletonCImpl, 11));
      this.provideLogInteractionUseCaseProvider = DoubleCheck.provider(new SwitchingProvider<LogInteractionUseCase>(singletonCImpl, 12));
      this.provideThemeDataStoreProvider = DoubleCheck.provider(new SwitchingProvider<DataStore<Preferences>>(singletonCImpl, 15));
      this.provideThemePreferenceRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<ThemePreferenceRepository>(singletonCImpl, 14));
      this.provideObserveThemeUseCaseProvider = DoubleCheck.provider(new SwitchingProvider<ObserveThemePreferenceUseCase>(singletonCImpl, 13));
      this.provideSetThemeUseCaseProvider = DoubleCheck.provider(new SwitchingProvider<SetThemePreferenceUseCase>(singletonCImpl, 16));
    }

    @Override
    public void injectFlashPoolApp(FlashPoolApp arg0) {
      injectFlashPoolApp2(arg0);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private FlashPoolApp injectFlashPoolApp2(FlashPoolApp instance) {
      FlashPoolApp_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.flashpool.data.worker.AnalyticsUploadWorker_AssistedFactory 
          return (T) new AnalyticsUploadWorker_AssistedFactory() {
            @Override
            public AnalyticsUploadWorker create(Context context, WorkerParameters params) {
              return new AnalyticsUploadWorker(context, params, singletonCImpl.provideAnalyticsRepositoryProvider.get());
            }
          };

          case 1: // com.flashpool.domain.repository.AnalyticsRepository 
          return (T) DataModule_ProvideAnalyticsRepositoryFactory.provideAnalyticsRepository(singletonCImpl.analyticsDao(), singletonCImpl.provideAnalyticsRemoteSyncProvider.get());

          case 2: // com.flashpool.data.local.FlashPoolDatabase 
          return (T) DataModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.flashpool.domain.repository.AnalyticsRemoteSync 
          return (T) FirebaseModule_ProvideAnalyticsRemoteSyncFactory.provideAnalyticsRemoteSync(singletonCImpl.firebaseAnalyticsRemoteSyncProvider.get());

          case 4: // com.flashpool.data.firebase.FirebaseAnalyticsRemoteSync 
          return (T) new FirebaseAnalyticsRemoteSync(singletonCImpl.provideFirestoreProvider.get());

          case 5: // com.google.firebase.firestore.FirebaseFirestore 
          return (T) FirebaseModule_ProvideFirestoreFactory.provideFirestore();

          case 6: // com.flashpool.domain.usecase.ObserveProductsUseCase 
          return (T) DomainModule_ProvideObserveProductsUseCaseFactory.provideObserveProductsUseCase(singletonCImpl.provideProductRepositoryProvider.get());

          case 7: // com.flashpool.domain.repository.ProductRepository 
          return (T) DataModule_ProvideProductRepositoryFactory.provideProductRepository(singletonCImpl.productDao(), singletonCImpl.provideCatalogApiProvider.get());

          case 8: // com.flashpool.data.remote.CatalogApi 
          return (T) DataModule_ProvideCatalogApiFactory.provideCatalogApi(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideJsonProvider.get());

          case 9: // okhttp3.OkHttpClient 
          return (T) DataModule_ProvideOkHttpClientFactory.provideOkHttpClient(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideJsonProvider.get());

          case 10: // kotlinx.serialization.json.Json 
          return (T) DataModule_ProvideJsonFactory.provideJson();

          case 11: // com.flashpool.domain.usecase.RefreshCatalogUseCase 
          return (T) DomainModule_ProvideRefreshCatalogUseCaseFactory.provideRefreshCatalogUseCase(singletonCImpl.provideProductRepositoryProvider.get());

          case 12: // com.flashpool.domain.usecase.LogInteractionUseCase 
          return (T) DomainModule_ProvideLogInteractionUseCaseFactory.provideLogInteractionUseCase(singletonCImpl.provideAnalyticsRepositoryProvider.get());

          case 13: // com.flashpool.domain.usecase.ObserveThemePreferenceUseCase 
          return (T) DomainModule_ProvideObserveThemeUseCaseFactory.provideObserveThemeUseCase(singletonCImpl.provideThemePreferenceRepositoryProvider.get());

          case 14: // com.flashpool.domain.repository.ThemePreferenceRepository 
          return (T) DataModule_ProvideThemePreferenceRepositoryFactory.provideThemePreferenceRepository(singletonCImpl.provideThemeDataStoreProvider.get());

          case 15: // androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> 
          return (T) DataModule_ProvideThemeDataStoreFactory.provideThemeDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 16: // com.flashpool.domain.usecase.SetThemePreferenceUseCase 
          return (T) DomainModule_ProvideSetThemeUseCaseFactory.provideSetThemeUseCase(singletonCImpl.provideThemePreferenceRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
