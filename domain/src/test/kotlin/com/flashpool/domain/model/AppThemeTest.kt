package com.flashpool.domain.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AppThemeTest {

    @Test
    fun light_forces_light() {
        assertFalse(resolveDarkTheme(AppTheme.LIGHT, systemInDarkTheme = true))
        assertFalse(resolveDarkTheme(AppTheme.LIGHT, systemInDarkTheme = false))
    }

    @Test
    fun dark_forces_dark() {
        assertTrue(resolveDarkTheme(AppTheme.DARK, systemInDarkTheme = false))
        assertTrue(resolveDarkTheme(AppTheme.DARK, systemInDarkTheme = true))
    }

    @Test
    fun system_defers_to_device_flag() {
        assertTrue(resolveDarkTheme(AppTheme.SYSTEM, systemInDarkTheme = true))
        assertFalse(resolveDarkTheme(AppTheme.SYSTEM, systemInDarkTheme = false))
    }
}
