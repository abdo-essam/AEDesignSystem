package com.ae.designsystem.components.ui.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Base surface component — a styled container using theme tokens.
 *
 * ```
 * AESurface(elevation = AETheme.elevation.md) {
 *     Text("Card content")
 * }
 * ```
 *
 * @param modifier Modifier for the surface root.
 * @param color Background color. Defaults to [AETheme.colors.surface].
 * @param shape Corner shape. Defaults to [AETheme.radius.md] rounded corners.
 * @param elevation Shadow elevation. Defaults to no elevation.
 * @param content Composable content slot.
 */
@Composable
public fun AESurface(
    modifier: Modifier = Modifier,
    color: Color = AETheme.colors.surface,
    shape: Shape = RoundedCornerShape(AETheme.radius.md),
    elevation: Dp = AETheme.elevation.none,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = elevation,
                shape = shape,
                clip = false,
            )
            .clip(shape)
            .background(color),
        content = content,
    )
}
