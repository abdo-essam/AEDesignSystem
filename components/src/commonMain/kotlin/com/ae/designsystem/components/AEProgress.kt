package com.ae.designsystem.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Linear progress indicator.
 *
 * Pass `null` for [progress] to show an indeterminate animation.
 *
 * ```
 * AELinearProgress(progress = 0.65f)  // 65%
 * AELinearProgress(progress = null)   // indeterminate
 * ```
 */
@Composable
public fun AELinearProgress(
    progress: Float? = null,
    modifier: Modifier = Modifier,
    color: Color = AETheme.colors.accent,
    trackColor: Color = AETheme.colors.border,
    height: Dp = 6.dp,
) {
    val radius = AETheme.radius

    // Indeterminate animation
    val infiniteTransition = rememberInfiniteTransition(label = "linearProgress")
    val animOffset by infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "linearProgressOffset",
    )

    val fraction = progress?.coerceIn(0f, 1f)
    val shape = RoundedCornerShape(radius.full)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(trackColor),
    ) {
        if (fraction != null) {
            // Determinate
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction)
                    .fillMaxHeight()
                    .clip(shape)
                    .background(color),
            )
        } else {
            // Indeterminate — sliding highlight block
            BoxWithConstraints(Modifier.fillMaxSize()) {
                val totalWidth = maxWidth
                val blockWidth = totalWidth * 0.4f
                val startX = totalWidth * animOffset

                Box(
                    modifier = Modifier
                        .offset(x = startX)
                        .width(blockWidth)
                        .fillMaxHeight()
                        .clip(shape)
                        .background(color),
                )
            }
        }
    }
}

/**
 * Circular progress indicator.
 *
 * Pass `null` for [progress] to show an indeterminate spinning animation.
 *
 * ```
 * AECircularProgress()               // indeterminate spinner
 * AECircularProgress(progress = 0.7f) // 70%
 * ```
 */
@Composable
public fun AECircularProgress(
    progress: Float? = null,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    strokeWidth: Dp = 4.dp,
    color: Color = AETheme.colors.accent,
    trackColor: Color = AETheme.colors.border,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "circularProgress")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearEasing),
        ),
        label = "circularProgressRotation",
    )

    val fraction = progress?.coerceIn(0f, 1f)

    Canvas(
        modifier = modifier.size(size),
    ) {
        val strokePx = strokeWidth.toPx()
        val diameter = minOf(this.size.width, this.size.height) - strokePx
        val topLeft = Offset(strokePx / 2f, strokePx / 2f)
        val arcSize = Size(diameter, diameter)

        // Track arc
        drawArc(
            color = trackColor,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(strokePx, cap = StrokeCap.Round),
        )

        if (fraction != null) {
            // Determinate arc
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * fraction,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(strokePx, cap = StrokeCap.Round),
            )
        } else {
            // Indeterminate — spinning arc
            drawArc(
                color = color,
                startAngle = rotation - 90f,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(strokePx, cap = StrokeCap.Round),
            )
        }
    }
}
