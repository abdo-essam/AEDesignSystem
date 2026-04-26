package com.ae.designsystem.foundation.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.ae.designsystem.foundation.color.AEColors
import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.icons.AEIconPack
import com.ae.designsystem.foundation.tokens.AEElevation
import com.ae.designsystem.foundation.tokens.AEMotion
import com.ae.designsystem.foundation.tokens.AERadius
import com.ae.designsystem.foundation.tokens.AESpacing
import com.ae.designsystem.foundation.typography.AETypography

/**
 * CompositionLocal providers for the AEDesignSystem token system.
 *
 * These are provided by [AETheme] and read by components via
 * [AETheme.colors], [AETheme.typography], etc.
 *
 * Using [staticCompositionLocalOf] because design tokens rarely change
 * during composition — they are set once at the theme root.
 */

public val LocalAEColors: ProvidableCompositionLocal<AEColors> =
    staticCompositionLocalOf {
        AEPalette.Zinc.toColors(AEAccent.Blue, darkTheme = false)
    }

public val LocalAETypography: ProvidableCompositionLocal<AETypography> =
    staticCompositionLocalOf {
        AETypography.default()
    }

public val LocalAESpacing: ProvidableCompositionLocal<AESpacing> =
    staticCompositionLocalOf {
        AESpacing()
    }

public val LocalAERadius: ProvidableCompositionLocal<AERadius> =
    staticCompositionLocalOf {
        AERadius()
    }

public val LocalAEElevation: ProvidableCompositionLocal<AEElevation> =
    staticCompositionLocalOf {
        AEElevation()
    }

public val LocalAEMotion: ProvidableCompositionLocal<AEMotion> =
    staticCompositionLocalOf {
        AEMotion()
    }

public val LocalAEIconPack: ProvidableCompositionLocal<AEIconPack> =
    staticCompositionLocalOf {
        AEIconPack.Default
    }
