package com.ae.designsystem.foundation.icons

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Interface for icon packs — resolves [AEIconToken] to actual [ImageVector] data.
 *
 * Swap the entire icon set by providing a different implementation:
 * ```
 * // Use filled icons everywhere
 * AETheme(iconPack = MyFilledIconPack()) { ... }
 *
 * // Or scope it to a section
 * ProvideIconPack(MyOutlinedPack) {
 *     // Only this subtree uses outlined icons
 * }
 * ```
 *
 * Implement this interface to create custom icon packs — e.g., mapping
 * to your own SVG icon set, or to a third-party icon library.
 */
public interface AEIconPack {
    /**
     * Resolves an [AEIconToken] to its [ImageVector] representation.
     *
     * Implementations should return a fallback icon (e.g., a simple square)
     * for unrecognized tokens rather than throwing.
     */
    public fun resolve(token: AEIconToken): ImageVector

    public companion object {
        /** The built-in default icon pack. */
        public val Default: AEIconPack = AEDefaultIconPack()
    }
}
