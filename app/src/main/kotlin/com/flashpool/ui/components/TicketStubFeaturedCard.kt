package com.flashpool.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.flashpool.domain.model.Product
import com.flashpool.ui.theme.countdownSmallStyle
import com.flashpool.ui.theme.countdownTextStyle
import com.flashpool.ui.theme.flashPoolExtras
import com.flashpool.ui.util.formatInrPrice
import java.util.concurrent.TimeUnit

@Composable
fun TicketStubFeaturedCard(
    product: Product,
    remainingMillis: Long?,
    onJoinPool: () -> Unit,
    modifier: Modifier = Modifier
) {
    val remaining = remainingMillis ?: 0L
    if (remaining <= 0L) return

    val extras = flashPoolExtras()
    val borderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .background(extras.ticketSurface)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "FEATURED",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Text(
                text = formatInrPrice(product.retailPrice),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }

        TicketPerforation(backgroundColor = MaterialTheme.colorScheme.background)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            StitchCountdownRow(
                remainingMillis = remaining,
                modifier = Modifier.weight(1f, fill = false)
            )
            FlashPoolPrimaryButton(
                text = "Join Pool",
                onClick = onJoinPool,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun StitchCountdownRow(
    remainingMillis: Long,
    modifier: Modifier = Modifier
) {
    val extras = flashPoolExtras()
    val hours = TimeUnit.MILLISECONDS.toHours(remainingMillis)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMillis) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(remainingMillis) % 60

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CountdownBlock("%02d".format(hours), "HR", extras)
        CountdownBlock("%02d".format(minutes), "MIN", extras)
        Text(":", style = countdownTextStyle, color = MaterialTheme.colorScheme.onSurface)
        CountdownBlock("%02d".format(seconds), "SEC", extras)
    }
}

@Composable
private fun CountdownBlock(
    value: String,
    label: String,
    extras: com.flashpool.ui.theme.FlashPoolExtraColors
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(extras.countdownBlock, RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(value, style = countdownSmallStyle, color = extras.onCountdownBlock)
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = extras.onCountdownBlock.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun FlashPoolPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        shape = RoundedCornerShape(999.dp),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text.uppercase(), style = MaterialTheme.typography.labelLarge)
    }
}
