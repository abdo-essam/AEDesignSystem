package com.ae.designsystem.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/** Position of a tooltip relative to its anchor. */
public enum class AETooltipPosition { Top, Bottom, Start, End }

/**
 * Tooltip — displays a contextual label above/below/beside its content.
 *
 * ```
 * AETooltip(tooltip = "Copy to clipboard") {
 *     AEButton(onClick = { copy() }) { AEText("Copy") }
 * }
 * ```
 *
 * @param tooltip The tooltip label string.
 * @param position Where the tooltip appears relative to [content].
 * @param content The anchor composable that triggers the tooltip on hover.
 */
@Composable
public fun AETooltip(
    tooltip: String,
    modifier: Modifier = Modifier,
    position: AETooltipPosition = AETooltipPosition.Top,
    content: @Composable () -> Unit,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius
    val motion = AETheme.motion

    var visible by remember { mutableStateOf(false) }

    val isVertical = position == AETooltipPosition.Top || position == AETooltipPosition.Bottom

    Box(modifier = modifier) {
        when (position) {
            AETooltipPosition.Top, AETooltipPosition.Start ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(motion.durationFast)) + slideInVertically(
                            animationSpec = tween(motion.durationFast),
                            initialOffsetY = { it / 2 },
                        ),
                        exit = fadeOut(tween(motion.durationFast)),
                    ) {
                        TooltipBox(label = tooltip)
                    }
                    if (position == AETooltipPosition.Top) Spacer(Modifier.height(spacing.xs))
                    content()
                }

            AETooltipPosition.Bottom, AETooltipPosition.End ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    content()
                    if (position == AETooltipPosition.Bottom) Spacer(Modifier.height(spacing.xs))
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(motion.durationFast)) + slideInVertically(
                            animationSpec = tween(motion.durationFast),
                            initialOffsetY = { -it / 2 },
                        ),
                        exit = fadeOut(tween(motion.durationFast)),
                    ) {
                        TooltipBox(label = tooltip)
                    }
                }
        }
    }
}

@Composable
private fun TooltipBox(label: String) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius

    val bg = if (colors.isLight) colors.textPrimary else colors.surfaceHover

    Box(
        modifier = Modifier
            .shadow(4.dp, RoundedCornerShape(radius.sm))
            .clip(RoundedCornerShape(radius.sm))
            .background(bg)
            .padding(horizontal = spacing.sm, vertical = spacing.xxs),
    ) {
        AEText(
            text = label,
            style = AETheme.typography.labelSmall,
            color = if (colors.isLight) colors.background else colors.textPrimary,
        )
    }
}
