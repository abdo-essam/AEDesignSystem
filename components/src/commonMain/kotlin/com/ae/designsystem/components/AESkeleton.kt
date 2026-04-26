package com.ae.designsystem.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Skeleton loading placeholder — shimmer animation on a rounded block.
 *
 * Use in place of real content while data is loading:
 * ```
 * AESkeleton(modifier = Modifier.fillMaxWidth().height(16.dp))
 * AESkeleton(modifier = Modifier.size(48.dp), shape = CircleShape)
 * ```
 *
 * @param modifier Size/layout of the skeleton block.
 * @param shape Corner shape. Defaults to [AETheme.radius.md] rounded.
 */
@Composable
public fun AESkeleton(
    modifier: Modifier = Modifier,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(AETheme.radius.md),
) {
    val colors = AETheme.colors

    val baseColor = if (colors.isLight) Color(0xFFE4E4E7) else Color(0xFF27272A)
    val shimmerColor = if (colors.isLight) Color(0xFFF4F4F5) else Color(0xFF3F3F46)

    val transition = rememberInfiniteTransition(label = "skeleton")
    val shimmerOffset by transition.animateFloat(
        initialValue = -1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmerOffset",
    )

    BoxWithConstraints(
        modifier = modifier
            .clip(shape)
            .background(baseColor),
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        val shimmerX = width * shimmerOffset

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            shimmerColor.copy(alpha = 0.6f),
                            Color.Transparent,
                        ),
                        start = Offset(shimmerX - width * 0.3f, 0f),
                        end = Offset(shimmerX + width * 0.3f, height),
                    ),
                ),
        )
    }
}

/**
 * Pre-built skeleton row with avatar + text lines — common loading pattern.
 *
 * ```
 * AESkeletonListItem()
 * ```
 */
@Composable
public fun AESkeletonListItem(
    modifier: Modifier = Modifier,
) {
    val spacing = AETheme.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        // Avatar placeholder
        AESkeleton(
            modifier = Modifier.size(40.dp),
            shape = androidx.compose.foundation.shape.CircleShape,
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.sm),
        ) {
            // Title line
            AESkeleton(modifier = Modifier.fillMaxWidth(0.6f).height(14.dp))
            // Subtitle line
            AESkeleton(modifier = Modifier.fillMaxWidth(0.9f).height(12.dp))
        }
    }
}
