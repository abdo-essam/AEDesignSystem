package com.ae.designsystem.foundation.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Renders an icon from the current [AEIconPack].
 *
 * ```
 * AEIcon(AEIcons.Heart)
 * AEIcon(AEIcons.Close, contentDescription = "Dismiss", tint = AETheme.colors.destructive)
 * ```
 *
 * @param token The semantic icon token to render.
 * @param modifier Modifier for the icon container.
 * @param contentDescription Accessibility description. Pass `null` for decorative icons.
 * @param tint Color tint applied to the icon. Defaults to [AETheme.colors.textPrimary].
 * @param size Icon size in dp. Defaults to 24dp.
 */
@Composable
public fun AEIcon(
    token: AEIconToken,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = AETheme.colors.textPrimary,
    size: Dp = 24.dp,
) {
    val iconPack = AETheme.icons
    val vector = iconPack.resolve(token)

    Image(
        imageVector = vector,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .then(
                if (contentDescription != null) {
                    Modifier.semantics {
                        this.contentDescription = contentDescription
                    }
                } else {
                    Modifier
                }
            ),
        colorFilter = ColorFilter.tint(tint),
    )
}
