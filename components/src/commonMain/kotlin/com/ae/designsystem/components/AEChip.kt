package com.ae.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Chip — compact interactive tag with optional leading/trailing icon.
 *
 * ```
 * AEChip(label = "Kotlin", leadingIcon = AEIcons.Star)
 * AEChip(label = "Remove", trailingIcon = AEIcons.Close, onClick = { ... })
 * ```
 */
@Composable
public fun AEChip(
    label: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: (() -> Unit)? = null,
    leadingIcon: AEIconToken? = null,
    trailingIcon: AEIconToken? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colors = AETheme.colors
    val radius = AETheme.radius
    val spacing = AETheme.spacing

    val bg = when {
        !enabled -> colors.surfaceHover.copy(alpha = 0.5f)
        selected -> colors.accentMuted
        else -> colors.surfaceHover
    }
    val border = when {
        selected -> colors.accent
        else -> colors.border
    }
    val textColor = when {
        !enabled -> colors.textMuted
        selected -> colors.accent
        else -> colors.textPrimary
    }

    val shape = RoundedCornerShape(radius.full)
    var mod = modifier
        .clip(shape)
        .background(bg)
        .border(1.dp, border, shape)

    if (onClick != null) {
        mod = mod.clickable(
            enabled = enabled,
            interactionSource = interactionSource,
            indication = null,
            role = Role.Button,
            onClick = onClick,
        )
    }

    Row(
        modifier = mod.padding(horizontal = spacing.md, vertical = spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        if (leadingIcon != null) {
            AEIcon(token = leadingIcon, size = 14.dp, tint = textColor)
        }
        AEText(text = label, style = AETheme.typography.labelMedium, color = textColor)
        if (trailingIcon != null) {
            AEIcon(token = trailingIcon, size = 14.dp, tint = textColor)
        }
    }
}
