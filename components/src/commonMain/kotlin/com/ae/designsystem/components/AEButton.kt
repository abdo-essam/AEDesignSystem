package com.ae.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.accessibility.MinimumInteractiveSize
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Button variants controlling visual style.
 */
public enum class AEButtonVariant {
    /** Filled accent background with contrast text */
    Filled,
    /** Transparent with accent border */
    Outlined,
    /** No background, text-only with hover state */
    Ghost,
    /** Destructive red styling */
    Destructive,
    /** Text-only link style */
    Link,
}

/**
 * Button size presets.
 */
public enum class AEButtonSize {
    Small,
    Medium,
    Large,
}

/**
 * Core button component — slot-based, token-driven, zero Material3.
 *
 * ```
 * AEButton(onClick = { }) {
 *     AEIcon(AEIcons.Heart, size = 16.dp)
 *     Spacer(Modifier.width(8.dp))
 *     Text("Like")
 * }
 * ```
 *
 * @param onClick Callback when clicked.
 * @param modifier Modifier for the button root.
 * @param variant Visual variant ([AEButtonVariant.Filled] by default).
 * @param size Size preset ([AEButtonSize.Medium] by default).
 * @param enabled Whether the button is interactive.
 * @param interactionSource Interaction source for state tracking.
 * @param content Slot-based content — typically text, icons, or both.
 */
@Composable
public fun AEButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: AEButtonVariant = AEButtonVariant.Filled,
    size: AEButtonSize = AEButtonSize.Medium,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit,
) {
    val colors = AETheme.colors
    val radius = AETheme.radius
    val spacing = AETheme.spacing

    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Resolve colors based on variant + state
    val backgroundColor = resolveBackgroundColor(variant, colors, enabled, isPressed, isHovered)
    val contentColor = resolveContentColor(variant, colors, enabled)

    // Resolve sizing
    val (horizontalPadding, verticalPadding, minHeight) = resolveSizing(size, spacing)

    val shape = RoundedCornerShape(radius.md)

    Box(
        modifier = modifier
            .defaultMinSize(
                minWidth = MinimumInteractiveSize,
                minHeight = minHeight,
            )
            .clip(shape)
            .background(backgroundColor)
            .then(
                if (variant == AEButtonVariant.Outlined) {
                    Modifier.background(Color.Transparent)
                } else {
                    Modifier
                }
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null, // We handle visual feedback manually
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .semantics { role = Role.Button }
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

// ── Internal helpers ──

@Composable
private fun resolveBackgroundColor(
    variant: AEButtonVariant,
    colors: com.ae.designsystem.foundation.color.AEColors,
    enabled: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
): Color {
    if (!enabled) {
        return when (variant) {
            AEButtonVariant.Filled, AEButtonVariant.Destructive -> colors.textMuted.copy(alpha = 0.3f)
            else -> Color.Transparent
        }
    }
    return when (variant) {
        AEButtonVariant.Filled -> when {
            isPressed -> colors.accentHover.copy(alpha = 0.85f)
            isHovered -> colors.accentHover
            else -> colors.accent
        }
        AEButtonVariant.Outlined -> when {
            isPressed -> colors.accentMuted
            isHovered -> colors.surfaceHover
            else -> Color.Transparent
        }
        AEButtonVariant.Ghost -> when {
            isPressed -> colors.surfaceHover.copy(alpha = 0.8f)
            isHovered -> colors.surfaceHover
            else -> Color.Transparent
        }
        AEButtonVariant.Destructive -> when {
            isPressed -> colors.destructive.copy(alpha = 0.85f)
            isHovered -> colors.destructive.copy(alpha = 0.9f)
            else -> colors.destructive
        }
        AEButtonVariant.Link -> Color.Transparent
    }
}

@Composable
private fun resolveContentColor(
    variant: AEButtonVariant,
    colors: com.ae.designsystem.foundation.color.AEColors,
    enabled: Boolean,
): Color {
    if (!enabled) return colors.textMuted
    return when (variant) {
        AEButtonVariant.Filled -> colors.textOnAccent
        AEButtonVariant.Outlined -> colors.accent
        AEButtonVariant.Ghost -> colors.textPrimary
        AEButtonVariant.Destructive -> colors.destructiveText
        AEButtonVariant.Link -> colors.accent
    }
}

private data class ButtonSizing(
    val horizontalPadding: androidx.compose.ui.unit.Dp,
    val verticalPadding: androidx.compose.ui.unit.Dp,
    val minHeight: androidx.compose.ui.unit.Dp,
)

@Composable
private fun resolveSizing(
    size: AEButtonSize,
    spacing: com.ae.designsystem.foundation.tokens.AESpacing,
): ButtonSizing {
    return when (size) {
        AEButtonSize.Small -> ButtonSizing(
            horizontalPadding = spacing.md,
            verticalPadding = spacing.xs,
            minHeight = 32.dp,
        )
        AEButtonSize.Medium -> ButtonSizing(
            horizontalPadding = spacing.lg,
            verticalPadding = spacing.sm,
            minHeight = 40.dp,
        )
        AEButtonSize.Large -> ButtonSizing(
            horizontalPadding = spacing.xl,
            verticalPadding = spacing.md,
            minHeight = 48.dp,
        )
    }
}
