package com.ae.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Horizontal divider line using theme border color.
 *
 * @param modifier Modifier for the divider.
 * @param color Divider color. Defaults to [AETheme.colors.border].
 * @param thickness Line thickness.
 */
@Composable
public fun AEDivider(
    modifier: Modifier = Modifier,
    color: Color = AETheme.colors.border,
    thickness: Dp = 1.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .background(color),
    )
}

/**
 * Vertical divider line using theme border color.
 *
 * @param modifier Modifier for the divider.
 * @param color Divider color. Defaults to [AETheme.colors.border].
 * @param thickness Line thickness.
 */
@Composable
public fun AEVerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = AETheme.colors.border,
    thickness: Dp = 1.dp,
) {
    Box(
        modifier = modifier
            .width(thickness)
            .background(color),
    )
}
