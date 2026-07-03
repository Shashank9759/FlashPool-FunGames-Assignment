package com.flashpool.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun TicketPerforation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.background
) {
    val lineColor = MaterialTheme.colorScheme.outlineVariant
    val notchRadius = 8.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxWidth().height(20.dp)) {
            val centerY = size.height / 2f
            val r = notchRadius.toPx()

            drawLine(
                color = lineColor,
                start = Offset(r * 2, centerY),
                end = Offset(size.width - r * 2, centerY),
                strokeWidth = 1.5f,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f))
            )

            drawCircle(color = backgroundColor, radius = r, center = Offset(0f, centerY))
            drawCircle(color = backgroundColor, radius = r, center = Offset(size.width, centerY))
            drawCircle(
                color = lineColor,
                radius = r,
                center = Offset(0f, centerY),
                style = Stroke(width = 1f)
            )
            drawCircle(
                color = lineColor,
                radius = r,
                center = Offset(size.width, centerY),
                style = Stroke(width = 1f)
            )
        }
    }
}
