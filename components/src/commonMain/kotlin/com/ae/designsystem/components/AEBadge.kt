package com.ae.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/** Visual style of a [AEBadge]. */
public enum class AEBadgeVariant { Default, Destructive, Success, Warning, Info, Outline }

/**
 * Badge — small labeled indicator for counts, status, and tags.
 *
 * ```
 * AEBadge(label = "New")
 * AEBadge(label = "Error", variant = AEBadgeVariant.Destructive)
 * ```
 */
@Composable
public fun AEBadge(
    label: String,
    modifier: Modifier = Modifier,
    variant: AEBadgeVariant = AEBadgeVariant.Default,
) {
    val colors = AETheme.colors
    val radius = AETheme.radius
    val spacing = AETheme.spacing

    val (bg, textColor) = when (variant) {
        AEBadgeVariant.Default -> colors.accent to colors.textOnAccent
        AEBadgeVariant.Destructive -> colors.destructive to colors.destructiveText
        AEBadgeVariant.Success -> colors.success to Color.White
        AEBadgeVariant.Warning -> colors.warning to Color(0xFF1C1917)
        AEBadgeVariant.Info -> colors.info to Color.White
        AEBadgeVariant.Outline -> Color.Transparent to colors.textPrimary
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(radius.full))
            .background(bg)
            .then(
                if (variant == AEBadgeVariant.Outline) {
                    Modifier.padding(horizontal = (spacing.sm.value - 1).dp, vertical = (spacing.xxs.value - 1).dp)
                } else {
                    Modifier.padding(horizontal = spacing.sm, vertical = spacing.xxs)
                }
            ),
        contentAlignment = Alignment.Center,
    ) {
        AEText(
            text = label,
            style = AETheme.typography.labelSmall,
            color = textColor,
        )
    }
}
