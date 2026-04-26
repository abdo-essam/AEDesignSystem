package com.ae.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

private val SwitchWidth = 44.dp
private val SwitchHeight = 24.dp
private val ThumbSize = 18.dp
private val ThumbPadding = 3.dp

/**
 * Toggle switch — animated thumb slide, token-driven.
 *
 * ```
 * var enabled by remember { mutableStateOf(false) }
 * AESwitch(checked = enabled, onCheckedChange = { enabled = it })
 * ```
 */
@Composable
public fun AESwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val motion = AETheme.motion

    val trackColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.textMuted.copy(alpha = 0.3f)
            checked -> colors.accent
            else -> colors.border
        },
        animationSpec = tween(motion.durationMedium),
        label = "switchTrack",
    )

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) SwitchWidth - ThumbSize - ThumbPadding else ThumbPadding,
        animationSpec = tween(motion.durationMedium),
        label = "switchThumb",
    )

    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Switch,
                onClick = { onCheckedChange(!checked) },
            )
            .semantics {
                role = Role.Switch
                stateDescription = if (checked) "On" else "Off"
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        // Track
        Box(
            modifier = Modifier
                .width(SwitchWidth)
                .height(SwitchHeight)
                .clip(RoundedCornerShape(SwitchHeight / 2))
                .background(trackColor),
        ) {
            // Thumb
            Box(
                modifier = Modifier
                    .padding(top = ThumbPadding, bottom = ThumbPadding)
                    .offset(x = thumbOffset)
                    .size(ThumbSize)
                    .clip(CircleShape)
                    .background(colors.textOnAccent),
            )
        }

        if (label != null) {
            AEText(
                text = label,
                style = AETheme.typography.bodyMedium,
                color = if (enabled) colors.textPrimary else colors.textMuted,
            )
        }
    }
}
