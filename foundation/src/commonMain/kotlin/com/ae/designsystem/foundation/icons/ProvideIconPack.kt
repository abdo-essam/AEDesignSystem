package com.ae.designsystem.foundation.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.ae.designsystem.foundation.theme.LocalAEIconPack

/**
 * Scoped icon pack override.
 *
 * Provides a different [AEIconPack] to a subtree without affecting
 * the rest of the app.
 *
 * ```
 * // Main app uses default filled icons
 * AETheme {
 *     // This section uses outlined icons
 *     ProvideIconPack(MyOutlinedPack) {
 *         AEIcon(AEIcons.Heart) // renders outlined heart
 *     }
 *     AEIcon(AEIcons.Heart) // renders filled heart
 * }
 * ```
 */
@Composable
public fun ProvideIconPack(
    iconPack: AEIconPack,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAEIconPack provides iconPack,
        content = content,
    )
}
