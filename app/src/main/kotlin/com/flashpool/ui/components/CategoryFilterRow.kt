package com.flashpool.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flashpool.domain.model.ProductCategory
import com.flashpool.ui.theme.surfaceContainer

private data class CategoryChip(val category: ProductCategory?, val label: String)

@Composable
fun CategoryFilterRow(
    selected: ProductCategory?,
    onSelect: (ProductCategory?) -> Unit,
    modifier: Modifier = Modifier
) {
    val chips = listOf(
        CategoryChip(null, "Home"),
        CategoryChip(ProductCategory.FMCG, "FMCG"),
        CategoryChip(ProductCategory.LIFESTYLE, "Lifestyle"),
        CategoryChip(ProductCategory.TECH, "Tech")
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(999.dp))
            .background(surfaceContainer())
            .padding(4.dp)
    ) {
        chips.forEach { chip ->
            CategoryChipItem(
                label = chip.label,
                selected = selected == chip.category,
                onClick = { onSelect(chip.category) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun CategoryChipItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bg by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.secondaryContainer
        } else {
            androidx.compose.ui.graphics.Color.Transparent
        },
        animationSpec = tween(250),
        label = "chipBg"
    )
    val fg by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.onSecondaryContainer
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(250),
        label = "chipFg"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(bg)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = fg,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
