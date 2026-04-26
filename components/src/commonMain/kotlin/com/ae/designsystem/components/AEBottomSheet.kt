package com.ae.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Bottom sheet — slides up from the bottom with a scrim overlay.
 *
 * ```
 * AEBottomSheet(
 *     visible = showSheet,
 *     onDismiss = { showSheet = false },
 *     title = "Sort By",
 * ) {
 *     // Sheet content
 *     Column { ... }
 * }
 * ```
 *
 * @param visible Whether the sheet is shown.
 * @param onDismiss Called when the user taps the scrim or swipes down.
 * @param title Optional title string in the sheet header.
 * @param dismissible Whether the scrim tap dismisses the sheet.
 * @param content Sheet body content slot.
 */
@Composable
public fun AEBottomSheet(
    visible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    dismissible: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius
    val motion = AETheme.motion

    Box(modifier = Modifier.fillMaxSize()) {
        // Scrim
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(motion.durationMedium)),
            exit = fadeOut(tween(motion.durationMedium)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .then(
                        if (dismissible) Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onDismiss,
                        ) else Modifier
                    ),
            )
        }

        // Sheet panel
        AnimatedVisibility(
            visible = visible,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically(
                animationSpec = tween(motion.durationMedium),
                initialOffsetY = { it },
            ) + fadeIn(tween(motion.durationMedium)),
            exit = slideOutVertically(
                animationSpec = tween(motion.durationMedium),
                targetOffsetY = { it },
            ) + fadeOut(tween(motion.durationFast)),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .shadow(AETheme.elevation.xl, RoundedCornerShape(topStart = radius.xl, topEnd = radius.xl))
                    .clip(RoundedCornerShape(topStart = radius.xl, topEnd = radius.xl))
                    .background(colors.surface)
                    .semantics {
                        dismiss { onDismiss(); true }
                    },
            ) {
                // Drag handle
                Box(
                    modifier = Modifier
                        .padding(top = spacing.md)
                        .width(40.dp)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(colors.border)
                        .align(Alignment.CenterHorizontally),
                )

                // Header
                if (title != null) {
                    Spacer(Modifier.height(spacing.md))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.xl),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AEText(text = title, style = AETheme.typography.headingSmall)
                        if (dismissible) {
                            AEButton(
                                onClick = onDismiss,
                                variant = AEButtonVariant.Ghost,
                                size = AEButtonSize.Small,
                            ) {
                                com.ae.designsystem.foundation.icons.AEIcon(
                                    token = com.ae.designsystem.foundation.icons.AEIcons.Close,
                                    tint = colors.textMuted,
                                    size = 18.dp,
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(spacing.sm))
                    AEDivider()
                } else {
                    Spacer(Modifier.height(spacing.lg))
                }

                // Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.xl),
                    content = content,
                )

                // Navigation bar spacer
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
            }
        }
    }
}
