package com.ae.designsystem.foundation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEColors
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.icons.AEIconPack
import com.ae.designsystem.foundation.tokens.AEElevation
import com.ae.designsystem.foundation.tokens.AEMotion
import com.ae.designsystem.foundation.tokens.AERadius
import com.ae.designsystem.foundation.tokens.AESpacing
import com.ae.designsystem.foundation.tokens.AEStylePreset
import com.ae.designsystem.foundation.typography.AEFontFamily
import com.ae.designsystem.foundation.typography.AETypography

/**
 * AEDesignSystem theme entry point.
 *
 * Wraps your composable tree to provide all design tokens via
 * [CompositionLocal]. Components inside this scope access tokens
 * through [AETheme.colors], [AETheme.typography], etc.
 *
 * ## Quick Start
 * ```
 * AETheme {
 *     AEButton(onClick = {}) { AEText("Hello") }
 * }
 * ```
 *
 * ## Custom Brand
 * ```
 * AETheme(
 *     palette = AEPalette.Slate,
 *     accent = AEAccent.Violet,
 *     preset = AEStylePreset.Soft,
 *     darkTheme = true,
 * ) { ... }
 * ```
 *
 * ## Full Override
 * ```
 * AETheme(
 *     colors = myBrandColors,
 *     typography = AETypography.default(myBrandFont),
 * ) { ... }
 * ```
 */
@Composable
public fun AETheme(
    palette: AEPalette = AEPalette.Zinc,
    accent: AEAccent = AEAccent.Blue,
    preset: AEStylePreset = AEStylePreset.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    iconPack: AEIconPack = AEIconPack.Default,
    colors: AEColors = palette.toColors(accent, darkTheme),
    typography: AETypography = AETypography.default(AEFontFamily.barlow()),
    spacing: AESpacing = preset.toSpacing(),
    radius: AERadius = preset.toRadius(),
    elevation: AEElevation = AEElevation(),
    motion: AEMotion = preset.toMotion(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalAEColors provides colors,
        LocalAETypography provides typography,
        LocalAESpacing provides spacing,
        LocalAERadius provides radius,
        LocalAEElevation provides elevation,
        LocalAEMotion provides motion,
        LocalAEIconPack provides iconPack,
        content = content,
    )
}

/**
 * Accessor object for current theme tokens.
 *
 * Must be called from within an [AETheme] scope.
 * ```
 * val bg = AETheme.colors.background
 * val title = AETheme.typography.headingLarge
 * ```
 */
public object AETheme {
    /** Current semantic color tokens. */
    public val colors: AEColors
        @Composable @ReadOnlyComposable
        get() = LocalAEColors.current

    /** Current typography scale. */
    public val typography: AETypography
        @Composable @ReadOnlyComposable
        get() = LocalAETypography.current

    /** Current spacing scale. */
    public val spacing: AESpacing
        @Composable @ReadOnlyComposable
        get() = LocalAESpacing.current

    /** Current corner radius scale. */
    public val radius: AERadius
        @Composable @ReadOnlyComposable
        get() = LocalAERadius.current

    /** Current elevation scale. */
    public val elevation: AEElevation
        @Composable @ReadOnlyComposable
        get() = LocalAEElevation.current

    /** Current motion/animation tokens. */
    public val motion: AEMotion
        @Composable @ReadOnlyComposable
        get() = LocalAEMotion.current

    /** Current icon pack. */
    public val icons: AEIconPack
        @Composable @ReadOnlyComposable
        get() = LocalAEIconPack.current
}
