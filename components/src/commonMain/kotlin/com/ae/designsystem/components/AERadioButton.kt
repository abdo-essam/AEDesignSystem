package com.ae.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

/**
 * Radio button — for use inside a mutually exclusive group.
 *
 * ```
 * AERadioButton(
 *     selected = selectedOption == option,
 *     onClick = { selectedOption = option },
 *     label = option,
 * )
 * ```
 */
@Composable
public fun AERadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val motion = AETheme.motion

    val ringColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.textMuted.copy(alpha = 0.4f)
            selected -> colors.accent
            else -> colors.border
        },
        animationSpec = tween(motion.durationFast),
        label = "radioRing",
    )

    val dotColor by animateColorAsState(
        targetValue = when {
            !enabled && selected -> colors.textMuted
            selected -> colors.accent
            else -> colors.background
        },
        animationSpec = tween(motion.durationFast),
        label = "radioDot",
    )

    Row(
        modifier = modifier
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .semantics {
                role = Role.RadioButton
                stateDescription = if (selected) "Selected" else "Not selected"
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        // Outer ring
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .border(2.dp, ringColor, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            // Inner dot
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(dotColor),
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
