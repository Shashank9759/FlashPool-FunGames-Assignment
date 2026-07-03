package com.flashpool.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

fun flashPoolTypography(): Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FlashPoolFontProvider.displayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 52.sp,
        letterSpacing = (-0.02).em
    ),
    displayMedium = TextStyle(
        fontFamily = FlashPoolFontProvider.displayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.01).em
    ),
    headlineMedium = TextStyle(
        fontFamily = FlashPoolFontProvider.displayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 31.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FlashPoolFontProvider.displayFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FlashPoolFontProvider.displayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FlashPoolFontProvider.displayFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FlashPoolFontProvider.bodyFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 29.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FlashPoolFontProvider.bodyFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FlashPoolFontProvider.bodyFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FlashPoolFontProvider.bodyFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FlashPoolFontProvider.bodyFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.05.em
    ),
    labelSmall = TextStyle(
        fontFamily = FlashPoolFontProvider.bodyFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.05.em
    )
)

val countdownTextStyle = TextStyle(
    fontFamily = FlashPoolFontProvider.monoFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 20.sp
)

val countdownSmallStyle = TextStyle(
    fontFamily = FlashPoolFontProvider.monoFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 16.sp
)
