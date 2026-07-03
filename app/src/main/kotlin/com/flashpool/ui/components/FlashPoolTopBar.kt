package com.flashpool.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.flashpool.R
import com.flashpool.domain.model.AppTheme
import com.flashpool.domain.model.resolveDarkTheme

@Composable
fun FlashPoolTopBar(
    appTheme: AppTheme,
    onThemeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val darkTheme = resolveDarkTheme(appTheme, isSystemInDarkTheme())

    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_flashpool_logo),
            contentDescription = "FlashPool logo",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = "FlashPool",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onThemeClick) {
            Icon(
                imageVector = if (darkTheme) Icons.Outlined.LightMode else Icons.Outlined.DarkMode,
                contentDescription = "Theme",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
