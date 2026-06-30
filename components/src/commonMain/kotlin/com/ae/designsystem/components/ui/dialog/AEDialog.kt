package com.ae.designsystem.components.ui.dialog

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.semantics.dialog
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText

/**
 * Modal dialog — slot-based with header, content, and action slots.
 *
 * ```
 * AEDialog(
 *     visible = showDialog,
 *     onDismiss = { showDialog = false },
 *     title = "Confirm Delete",
 *     confirmButton = {
 *         AEButton(onClick = { doDelete(); showDialog = false },
 *                  variant = AEButtonVariant.Destructive) {
 *             AEText("Delete")
 *         }
 *     },
 * ) {
 *     AEText("This action cannot be undone.")
 * }
 * ```
 *
 * @param visible Whether the dialog is shown.
 * @param onDismiss Called when the user dismisses (backdrop tap or back).
 * @param title Optional title string rendered in the header.
 * @param dismissible Whether backdrop tap / back gesture dismisses the dialog.
 * @param icon Optional icon shown beside the title.
 * @param confirmButton Slot for the primary action button.
 * @param dismissButton Slot for the secondary/cancel button.
 * @param content Main body content slot.
 */
@Composable
public fun AEDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    dismissible: Boolean = true,
    confirmButton: (@Composable () -> Unit)? = null,
    dismissButton: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (!visible) return

    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius

    Dialog(
        onDismissRequest = { if (dismissible) onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = dismissible,
            dismissOnClickOutside = dismissible,
        ),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .shadow(AETheme.elevation.xl, RoundedCornerShape(radius.xl))
                .clip(RoundedCornerShape(radius.xl))
                .background(colors.surface)
                .padding(spacing.xl)
                .semantics { dialog() },
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            // Header
            if (title != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AEText(
                        text = title,
                        style = AETheme.typography.headingSmall,
                    )
                    if (dismissible) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(radius.sm))
                                .clickable { onDismiss() },
                            contentAlignment = Alignment.Center,
                        ) {
                            AEIcon(
                                token = AEIcons.Close,
                                tint = colors.textMuted,
                                size = 18.dp,
                            )
                        }
                    }
                }
                AEDivider()
            }

            // Content
            content()

            // Actions
            if (confirmButton != null || dismissButton != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm, Alignment.End),
                ) {
                    dismissButton?.invoke()
                    confirmButton?.invoke()
                }
            }
        }
    }
}