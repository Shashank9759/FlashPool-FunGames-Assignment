package com.flashpool.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.flashpool.domain.model.AppTheme
import com.flashpool.domain.repository.ThemePreferenceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ThemePreferenceRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val ioDispatcher: CoroutineDispatcher
) : ThemePreferenceRepository {

    override fun observeTheme(): Flow<AppTheme> =
        dataStore.data.map { prefs ->
            when (prefs[KEY_THEME]) {
                AppTheme.DARK.name -> AppTheme.DARK
                AppTheme.SYSTEM.name -> AppTheme.SYSTEM
                else -> AppTheme.LIGHT
            }
        }

    override suspend fun setTheme(theme: AppTheme): Unit = withContext(ioDispatcher) {
        dataStore.edit { prefs ->
            prefs[KEY_THEME] = theme.name
        }
    }

    companion object {
        val KEY_THEME = stringPreferencesKey("app_theme")
    }
}
