package com.flashpool.domain.model

enum class AppTheme {
    LIGHT,
    DARK,
    SYSTEM
}

fun resolveDarkTheme(preference: AppTheme, systemInDarkTheme: Boolean): Boolean = when (preference) {
    AppTheme.LIGHT -> false
    AppTheme.DARK -> true
    AppTheme.SYSTEM -> systemInDarkTheme
}
