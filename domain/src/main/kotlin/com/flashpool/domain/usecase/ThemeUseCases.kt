package com.flashpool.domain.usecase

import com.flashpool.domain.model.AppTheme
import com.flashpool.domain.repository.ThemePreferenceRepository
import kotlinx.coroutines.flow.Flow

class ObserveThemePreferenceUseCase(
    private val repository: ThemePreferenceRepository
) {
    operator fun invoke(): Flow<AppTheme> = repository.observeTheme()
}

class SetThemePreferenceUseCase(
    private val repository: ThemePreferenceRepository
) {
    suspend operator fun invoke(theme: AppTheme) {
        repository.setTheme(theme)
    }
}
