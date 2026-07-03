package com.flashpool.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flashpool.domain.model.AppTheme
import com.flashpool.domain.usecase.ObserveThemePreferenceUseCase
import com.flashpool.domain.usecase.SetThemePreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    observeThemePreference: ObserveThemePreferenceUseCase,
    private val setThemePreference: SetThemePreferenceUseCase
) : ViewModel() {

    val theme: StateFlow<AppTheme> = observeThemePreference()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppTheme.SYSTEM)

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            setThemePreference(theme)
        }
    }
}
