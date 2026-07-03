package com.flashpool.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.flashpool.ui.theme.FlashPoolTokens

@Composable
fun shimmerBrush(): Brush {
    val isDark = MaterialTheme.colorScheme.background == FlashPoolTokens.Dark.background
    val base = if (isDark) FlashPoolTokens.Dark.surfaceContainerHigh else FlashPoolTokens.Light.surfaceContainer
    val highlight = if (isDark) FlashPoolTokens.Dark.surfaceContainerHighest else FlashPoolTokens.Light.surfaceContainerHigh

    val transition = rememberInfiniteTransition(label = "shimmer")
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1100, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerOffset"
    )
    return Brush.linearGradient(
        colors = listOf(base, highlight, base),
        start = Offset(offset - 300f, 0f),
        end = Offset(offset, 0f)
    )
}
