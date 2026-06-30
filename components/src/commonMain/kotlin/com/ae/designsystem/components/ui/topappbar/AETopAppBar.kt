package com.ae.designsystem.components.ui.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText

/**
 * Top app bar — slot-based with navigation icon, title, and actions row.
 *
 * ```
 * AETopAppBar(
 *     title = "Settings",
 *     navigationIcon = AEIcons.ArrowLeft,
 *     onNavigationClick = { navController.popBackStack() },
 *     actions = {
 *         AEButton(onClick = { }, variant = AEButtonVariant.Ghost) {
 *             AEIcon(AEIcons.Search, tint = AETheme.colors.textPrimary, size = 20.dp)
 *         }
 *     },
 * )
 * ```
 *
 * @param title Title string shown in the center/start of the bar.
 * @param navigationIcon Optional leading icon token (e.g. back arrow).
 * @param onNavigationClick Called when [navigationIcon] is tapped.
 * @param actions Slot for trailing action buttons.
 * @param elevated Whether to draw a shadow beneath the bar.
 */
@Composable
public fun AETopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: AEIconToken? = null,
    onNavigationClick: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    elevated: Boolean = false,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val elevation = AETheme.elevation

    Box(
        modifier = modifier
            .fillMaxWidth()
            .then(if (elevated) Modifier.shadow(elevation.sm) else Modifier)
            .background(colors.surface)
            .windowInsetsPadding(WindowInsets.statusBars)
            .height(56.dp),
        contentAlignment = Alignment.Center,
    ) {
        // Navigation icon — start
        if (navigationIcon != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = spacing.sm),
            ) {
                AEButton(
                    onClick = { onNavigationClick?.invoke() },
                    variant = AEButtonVariant.Ghost,
                    size = AEButtonSize.Small,
                ) {
                    AEIcon(token = navigationIcon, tint = colors.textPrimary, size = 22.dp)
                }
            }
        }

        // Title — center
        AEText(
            text = title,
            style = AETheme.typography.headingSmall,
            color = colors.textPrimary,
        )

        // Actions — end
        if (actions != null) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
                content = actions,
            )
        }
    }

    AEDivider()
}