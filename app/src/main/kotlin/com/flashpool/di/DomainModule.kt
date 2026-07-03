package com.flashpool.di

import com.flashpool.domain.repository.AnalyticsRepository
import com.flashpool.domain.repository.ProductRepository
import com.flashpool.domain.usecase.LogInteractionUseCase
import com.flashpool.domain.usecase.ObserveProductsUseCase
import com.flashpool.domain.usecase.ObserveThemePreferenceUseCase
import com.flashpool.domain.usecase.RefreshCatalogUseCase
import com.flashpool.domain.usecase.SetThemePreferenceUseCase
import com.flashpool.domain.repository.ThemePreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideObserveProductsUseCase(repo: ProductRepository) = ObserveProductsUseCase(repo)

    @Provides
    @Singleton
    fun provideRefreshCatalogUseCase(repo: ProductRepository) = RefreshCatalogUseCase(repo)

    @Provides
    @Singleton
    fun provideLogInteractionUseCase(repo: AnalyticsRepository) = LogInteractionUseCase(repo)

    @Provides
    @Singleton
    fun provideObserveThemeUseCase(repo: ThemePreferenceRepository) = ObserveThemePreferenceUseCase(repo)

    @Provides
    @Singleton
    fun provideSetThemeUseCase(repo: ThemePreferenceRepository) = SetThemePreferenceUseCase(repo)
}
