package com.flashpool.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.flashpool.domain.model.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
class ThemePreferenceRepositoryImplTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var tempFile: File
    private lateinit var repository: ThemePreferenceRepositoryImpl

    @Before
    fun setup() {
        tempFile = File.createTempFile("theme_prefs", ".preferences_pb")
        dataStore = PreferenceDataStoreFactory.create(
            scope = CoroutineScope(dispatcher),
            produceFile = { tempFile }
        )
        repository = ThemePreferenceRepositoryImpl(dataStore, dispatcher)
    }

    @After
    fun tearDown() {
        tempFile.delete()
    }

    @Test
    fun defaults_to_light() = runTest(dispatcher) {
        assertEquals(AppTheme.LIGHT, repository.observeTheme().first())
    }

    @Test
    fun persists_light_dark_and_system() = runTest(dispatcher) {
        repository.setTheme(AppTheme.DARK)
        advanceUntilIdle()
        assertEquals(AppTheme.DARK, repository.observeTheme().first())

        repository.setTheme(AppTheme.SYSTEM)
        advanceUntilIdle()
        assertEquals(AppTheme.SYSTEM, repository.observeTheme().first())

        repository.setTheme(AppTheme.LIGHT)
        advanceUntilIdle()
        assertEquals(AppTheme.LIGHT, repository.observeTheme().first())
    }
}
