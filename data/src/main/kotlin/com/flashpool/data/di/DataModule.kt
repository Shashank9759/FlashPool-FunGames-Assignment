package com.flashpool.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.flashpool.data.local.FlashPoolDatabase
import com.flashpool.data.local.dao.AnalyticsDao
import com.flashpool.data.local.dao.ProductDao
import com.flashpool.data.remote.CatalogApi
import com.flashpool.data.remote.FakeCatalogInterceptor
import com.flashpool.data.preferences.ThemePreferenceRepositoryImpl
import com.flashpool.data.repository.AnalyticsRepositoryImpl
import com.flashpool.data.repository.ProductRepositoryImpl
import com.flashpool.domain.repository.AnalyticsRemoteSync
import com.flashpool.domain.repository.AnalyticsRepository
import com.flashpool.domain.repository.ProductRepository
import com.flashpool.domain.repository.ThemePreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Singleton

private val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FlashPoolDatabase =
        Room.databaseBuilder(context, FlashPoolDatabase::class.java, "flashpool.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideProductDao(db: FlashPoolDatabase): ProductDao = db.productDao()

    @Provides
    fun provideAnalyticsDao(db: FlashPoolDatabase): AnalyticsDao = db.analyticsDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        json: Json
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(FakeCatalogInterceptor(context, json))
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .build()

    @Provides
    @Singleton
    fun provideCatalogApi(client: OkHttpClient, json: Json): CatalogApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://mock.flashpool.internal/api/v1/")
            .client(client)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(CatalogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productDao: ProductDao,
        catalogApi: CatalogApi
    ): ProductRepository = ProductRepositoryImpl(productDao, catalogApi, Dispatchers.IO)

    @Provides
    @Singleton
    fun provideAnalyticsRepository(
        analyticsDao: AnalyticsDao,
        remoteSync: AnalyticsRemoteSync
    ): AnalyticsRepository =
        AnalyticsRepositoryImpl(analyticsDao, remoteSync, Dispatchers.IO)

    @Provides
    @Singleton
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.themeDataStore

    @Provides
    @Singleton
    fun provideThemePreferenceRepository(
        dataStore: DataStore<Preferences>
    ): ThemePreferenceRepository = ThemePreferenceRepositoryImpl(dataStore, Dispatchers.IO)
}
