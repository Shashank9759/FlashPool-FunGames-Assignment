package com.flashpool.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flashpool.domain.model.resolveDarkTheme
import com.flashpool.ui.components.ThemePickerSheet
import com.flashpool.ui.feed.ProductFeedScreen
import com.flashpool.ui.theme.FlashPoolTheme
import com.flashpool.ui.theme.ThemeViewModel

@Composable
fun FlashPoolAppRoot(
    themeViewModel: ThemeViewModel = hiltViewModel()
) {
    val appTheme by themeViewModel.theme.collectAsStateWithLifecycle()
    val systemInDarkTheme = isSystemInDarkTheme()
    val darkTheme = resolveDarkTheme(appTheme, systemInDarkTheme)
    var showThemePicker by rememberSaveable { mutableStateOf(false) }

    FlashPoolTheme(darkTheme = darkTheme) {
        ProductFeedScreen(
            appTheme = appTheme,
            onThemeClick = { showThemePicker = true }
        )

        if (showThemePicker) {
            ThemePickerSheet(
                selected = appTheme,
                onSelect = { theme ->
                    themeViewModel.setTheme(theme)
                    showThemePicker = false
                },
                onDismiss = { showThemePicker = false }
            )
        }
    }
}
