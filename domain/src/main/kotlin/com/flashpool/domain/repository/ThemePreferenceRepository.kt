package com.flashpool.domain.repository

import com.flashpool.domain.model.AppTheme
import kotlinx.coroutines.flow.Flow

interface ThemePreferenceRepository {
    fun observeTheme(): Flow<AppTheme>
    suspend fun setTheme(theme: AppTheme)
}
