package com.flashpool.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.flashpool.R

object FlashPoolFontProvider {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val displayFamily: FontFamily = FontFamily(
        Font(googleFont = GoogleFont("Archivo Narrow"), fontProvider = provider, weight = androidx.compose.ui.text.font.FontWeight.Bold),
        Font(googleFont = GoogleFont("Archivo Narrow"), fontProvider = provider, weight = androidx.compose.ui.text.font.FontWeight.SemiBold),
        Font(googleFont = GoogleFont("Archivo Narrow"), fontProvider = provider, weight = androidx.compose.ui.text.font.FontWeight.Medium)
    )

    val bodyFamily: FontFamily = FontFamily(
        Font(googleFont = GoogleFont("Fira Sans"), fontProvider = provider, weight = androidx.compose.ui.text.font.FontWeight.Normal),
        Font(googleFont = GoogleFont("Fira Sans"), fontProvider = provider, weight = androidx.compose.ui.text.font.FontWeight.SemiBold)
    )

    val monoFamily: FontFamily = FontFamily(
        Font(googleFont = GoogleFont("JetBrains Mono"), fontProvider = provider, weight = androidx.compose.ui.text.font.FontWeight.Bold)
    )
}
