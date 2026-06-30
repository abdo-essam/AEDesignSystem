package com.ae.designsystem.components.ui.dropdown

import androidx.compose.animation.*
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.text.AEText

/**
 * A single item in an [AEDropdownMenu].
 *
 * @param label Display text.
 * @param icon Optional leading icon.
 * @param destructive Whether to render in destructive (red) colors.
 * @param enabled Whether this item is interactive.
 * @param onClick Callback when tapped.
 */
public data class AEDropdownItem(
    val label: String,
    val icon: AEIconToken? = null,
    val destructive: Boolean = false,
    val enabled: Boolean = true,
    val onClick: () -> Unit,
)

/**
 * Dropdown menu — anchored popup with a list of [AEDropdownItem]s.
 *
 * ```
 * var expanded by remember { mutableStateOf(false) }
 *
 * Box {
 *     AEButton(onClick = { expanded = true }) { AEText("Options") }
 *     AEDropdownMenu(
 *         expanded = expanded,
 *         onDismiss = { expanded = false },
 *         items = listOf(
 *             AEDropdownItem("Edit",   icon = AEIcons.Edit)   { doEdit() },
 *             AEDropdownItem("Delete", icon = AEIcons.Delete, destructive = true) { doDelete() },
 *         ),
 *     )
 * }
 * ```
 *
 * @param expanded Whether the menu is shown.
 * @param onDismiss Called when the user taps outside.
 * @param items List of menu items to display.
 */
@Composable
public fun AEDropdownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    items: List<AEDropdownItem>,
    modifier: Modifier = Modifier,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius
    val motion = AETheme.motion

    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn(tween(motion.durationFast)) +
                expandVertically(tween(motion.durationFast), expandFrom = Alignment.Top) +
                scaleIn(tween(motion.durationFast), initialScale = 0.92f),
        exit = fadeOut(tween(motion.durationFast)) +
               shrinkVertically(tween(motion.durationFast), shrinkTowards = Alignment.Top) +
               scaleOut(tween(motion.durationFast), targetScale = 0.92f),
    ) {
        Column(
            modifier = modifier
                .defaultMinSize(minWidth = 180.dp)
                .shadow(AETheme.elevation.lg, RoundedCornerShape(radius.lg))
                .clip(RoundedCornerShape(radius.lg))
                .background(colors.surface)
                .border(1.dp, colors.border, RoundedCornerShape(radius.lg))
                .padding(vertical = spacing.xs),
        ) {
            items.forEach { item ->
                DropdownMenuItem(item = item, onDismiss = onDismiss)
            }
        }
    }
}

@Composable
private fun DropdownMenuItem(item: AEDropdownItem, onDismiss: () -> Unit) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing

    val textColor = when {
        !item.enabled -> colors.textMuted
        item.destructive -> colors.destructive
        else -> colors.textPrimary
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = item.enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Button,
                onClick = {
                    item.onClick()
                    onDismiss()
                },
            )
            .padding(horizontal = spacing.md, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        if (item.icon != null) {
            AEIcon(token = item.icon, tint = textColor, size = 16.dp)
        } else {
            Spacer(Modifier.width(16.dp))
        }
        AEText(
            text = item.label,
            style = AETheme.typography.bodyMedium,
            color = textColor,
        )
    }
}