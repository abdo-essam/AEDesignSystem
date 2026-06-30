package com.ae.designsystem.components.ui.checkbox

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.components.ui.text.AEText

/**
 * Checkbox component — token-driven, zero Material3.
 *
 * ```
 * var checked by remember { mutableStateOf(false) }
 * AECheckbox(
 *     checked = checked,
 *     onCheckedChange = { checked = it },
 *     label = "Accept terms",
 * )
 * ```
 */
@Composable
public fun AECheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colors = AETheme.colors
    val radius = AETheme.radius
    val spacing = AETheme.spacing
    val motion = AETheme.motion

    val boxColor by animateColorAsState(
        targetValue = when {
            !enabled -> if (checked) colors.textMuted else Color.Transparent
            checked -> colors.accent
            else -> Color.Transparent
        },
        animationSpec = tween(motion.durationFast),
        label = "checkboxColor",
    )

    val borderColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.textMuted.copy(alpha = 0.4f)
            checked -> colors.accent
            else -> colors.border
        },
        animationSpec = tween(motion.durationFast),
        label = "checkboxBorder",
    )

    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Checkbox,
                onClick = { onCheckedChange(!checked) },
            )
            .semantics {
                role = Role.Checkbox
                stateDescription = if (checked) "Checked" else "Unchecked"
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        // Checkbox box
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(RoundedCornerShape(radius.sm))
                .background(boxColor)
                .border(1.5.dp, borderColor, RoundedCornerShape(radius.sm)),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) {
                AEIcon(
                    token = AEIcons.Check,
                    tint = if (enabled) colors.textOnAccent else colors.textMuted,
                    size = 12.dp,
                )
            }
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