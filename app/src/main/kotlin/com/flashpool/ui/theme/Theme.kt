package com.flashpool.ui.theme

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class FlashPoolExtraColors(
    val ticketSurface: Color,
    val countdownBlock: Color,
    val onCountdownBlock: Color,
    val bannerOverlay: Color
)

val LocalFlashPoolExtraColors = staticCompositionLocalOf {
    FlashPoolExtraColors(
        ticketSurface = Color.White,
        countdownBlock = Color.Black,
        onCountdownBlock = Color.White,
        bannerOverlay = Color.Red
    )
}

private fun lightScheme(): ColorScheme = with(FlashPoolTokens.Light) {
    lightColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        outline = outline,
        outlineVariant = outlineVariant,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        inversePrimary = inversePrimary
    )
}

private fun darkScheme(): ColorScheme = with(FlashPoolTokens.Dark) {
    darkColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = tertiary,
        onTertiary = onTertiary,
        tertiaryContainer = tertiaryContainer,
        onTertiaryContainer = onTertiaryContainer,
        error = error,
        onError = onError,
        errorContainer = errorContainer,
        onErrorContainer = onErrorContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = onSurfaceVariant,
        outline = outline,
        outlineVariant = outlineVariant,
        inverseSurface = inverseSurface,
        inverseOnSurface = inverseOnSurface,
        inversePrimary = inversePrimary
    )
}

@Composable
private fun animatedColorScheme(darkTheme: Boolean): ColorScheme {
    val target = if (darkTheme) darkScheme() else lightScheme()

    val primary by animateColorAsState(target.primary, tween(350), label = "primary")
    val background by animateColorAsState(target.background, tween(350), label = "background")
    val surface by animateColorAsState(target.surface, tween(350), label = "surface")
    val onSurface by animateColorAsState(target.onSurface, tween(350), label = "onSurface")
    val secondaryContainer by animateColorAsState(target.secondaryContainer, tween(350), label = "secondaryContainer")
    val primaryContainer by animateColorAsState(target.primaryContainer, tween(350), label = "primaryContainer")

    return target.copy(
        primary = primary,
        background = background,
        surface = surface,
        onSurface = onSurface,
        secondaryContainer = secondaryContainer,
        primaryContainer = primaryContainer
    )
}

@Composable
fun FlashPoolTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    Crossfade(targetState = darkTheme, animationSpec = tween(300), label = "themeCrossfade") { isDark ->
        val extras = if (isDark) {
            FlashPoolExtraColors(
                ticketSurface = FlashPoolTokens.Dark.ticketSurface,
                countdownBlock = FlashPoolTokens.Dark.countdownBlock,
                onCountdownBlock = FlashPoolTokens.Dark.onCountdownBlock,
                bannerOverlay = FlashPoolTokens.Dark.bannerOverlay
            )
        } else {
            FlashPoolExtraColors(
                ticketSurface = FlashPoolTokens.Light.ticketSurface,
                countdownBlock = FlashPoolTokens.Light.countdownBlock,
                onCountdownBlock = FlashPoolTokens.Light.onCountdownBlock,
                bannerOverlay = FlashPoolTokens.Light.bannerOverlay
            )
        }
        CompositionLocalProvider(LocalFlashPoolExtraColors provides extras) {
            MaterialTheme(
                colorScheme = animatedColorScheme(isDark),
                typography = flashPoolTypography(),
                content = content
            )
        }
    }
}

@Composable
fun flashPoolExtras(): FlashPoolExtraColors = LocalFlashPoolExtraColors.current

@Composable
fun surfaceContainer(): Color =
    if (MaterialTheme.colorScheme.background == FlashPoolTokens.Dark.background) {
        FlashPoolTokens.Dark.surfaceContainer
    } else {
        FlashPoolTokens.Light.surfaceContainer
    }

@Composable
fun surfaceContainerLow(): Color =
    if (MaterialTheme.colorScheme.background == FlashPoolTokens.Dark.background) {
        FlashPoolTokens.Dark.surfaceContainerLow
    } else {
        FlashPoolTokens.Light.surfaceContainerLow
    }

@Composable
fun surfaceContainerLowest(): Color =
    if (MaterialTheme.colorScheme.background == FlashPoolTokens.Dark.background) {
        FlashPoolTokens.Dark.surfaceContainerLowest
    } else {
        FlashPoolTokens.Light.surfaceContainerLowest
    }
