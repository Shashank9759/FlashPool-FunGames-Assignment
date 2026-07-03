package com.flashpool.data.di

import com.flashpool.data.firebase.FirebaseAnalyticsRemoteSync
import com.flashpool.domain.repository.AnalyticsRemoteSync
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAnalyticsRemoteSync(
        impl: FirebaseAnalyticsRemoteSync
    ): AnalyticsRemoteSync = impl
}
