package com.ae.designsystem.components.ui.navigationbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText

/**
 * A single item in an [AENavigationBar].
 *
 * @param icon Icon token for this destination.
 * @param label Label string shown below the icon.
 * @param selected Whether this item is currently active.
 * @param badge Optional badge number shown over the icon (0 = no badge).
 */
public data class AENavItem(
    val icon: AEIconToken,
    val label: String,
    val selected: Boolean = false,
    val badge: Int = 0,
)

/**
 * Bottom navigation bar — up to 5 destinations with animated indicator pill.
 *
 * ```
 * var currentDest by remember { mutableIntStateOf(0) }
 * AENavigationBar(
 *     items = listOf(
 *         AENavItem(AEIcons.Home,     "Home",    currentDest == 0),
 *         AENavItem(AEIcons.Search,   "Explore", currentDest == 1),
 *         AENavItem(AEIcons.Heart,    "Saved",   currentDest == 2, badge = 3),
 *         AENavItem(AEIcons.User,     "Profile", currentDest == 3),
 *     ),
 *     onItemSelected = { currentDest = it },
 * )
 * ```
 */
@Composable
public fun AENavigationBar(
    items: List<AENavItem>,
    onItemSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius
    val motion = AETheme.motion

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        AEDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.surface)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { index, item ->
                NavBarItem(
                    item = item,
                    onClick = { onItemSelected(index) },
                    motion = motion.durationFast,
                )
            }
        }
    }
}

@Composable
private fun NavBarItem(
    item: AENavItem,
    onClick: () -> Unit,
    motion: Int,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius

    val iconTint by animateColorAsState(
        targetValue = if (item.selected) colors.accent else colors.textMuted,
        animationSpec = tween(motion),
        label = "navIconTint",
    )
    val pillColor by animateColorAsState(
        targetValue = if (item.selected) colors.accentMuted else colors.background.copy(alpha = 0f),
        animationSpec = tween(motion),
        label = "navPill",
    )

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Tab,
                onClick = onClick,
            )
            .semantics {
                role = Role.Tab
                selected = item.selected
            }
            .padding(horizontal = spacing.sm),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.xxs),
    ) {
        // Icon with pill background + optional badge
        Box {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(radius.full))
                    .background(pillColor)
                    .padding(horizontal = spacing.lg, vertical = spacing.xs),
                contentAlignment = Alignment.Center,
            ) {
                AEIcon(token = item.icon, tint = iconTint, size = 22.dp)
            }

            // Badge dot/count
            if (item.badge > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 4.dp, y = (-4).dp)
                        .defaultMinSize(minWidth = 16.dp, minHeight = 16.dp)
                        .clip(RoundedCornerShape(radius.full))
                        .background(colors.destructive)
                        .padding(horizontal = 3.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    AEText(
                        text = if (item.badge > 99) "99+" else item.badge.toString(),
                        style = AETheme.typography.labelSmall,
                        color = colors.destructiveText,
                    )
                }
            }
        }

        // Label
        AEText(
            text = item.label,
            style = AETheme.typography.labelSmall,
            color = iconTint,
        )
    }
}