package com.ae.designsystem.foundation.color

import androidx.compose.ui.graphics.Color

/**
 * Neutral palette presets — define the grayscale foundation for a theme.
 * Each palette provides full light and dark [AEColors] when combined
 * with an [AEAccent].
 *
 * Usage:
 * ```
 * AETheme(palette = AEPalette.Zinc, accent = AEAccent.Blue) { ... }
 * ```
 */
public enum class AEPalette {
    /** Pure, clean neutral grays */
    Zinc,

    /** Cool blue-tinted neutrals */
    Slate,

    /** Warm earth-toned neutrals */
    Stone,

    /** True neutral gray scale */
    Neutral;

    /**
     * Resolves this palette + accent into a full semantic [AEColors] instance.
     */
    public fun toColors(accent: AEAccent, darkTheme: Boolean): AEColors {
        return if (darkTheme) toDarkColors(accent) else toLightColors(accent)
    }

    private fun toLightColors(accent: AEAccent): AEColors {
        val neutrals = lightNeutrals()
        return AEColors(
            background = neutrals.background,
            backgroundSecondary = neutrals.backgroundSecondary,
            surface = neutrals.surface,
            surfaceHover = neutrals.surfaceHover,
            textPrimary = neutrals.textPrimary,
            textSecondary = neutrals.textSecondary,
            textMuted = neutrals.textMuted,
            textOnAccent = accent.onAccent,
            accent = accent.primary,
            accentHover = accent.hover,
            accentMuted = accent.muted.copy(alpha = 0.12f),
            destructive = Color(0xFFEF4444),
            destructiveText = Color.White,
            success = Color(0xFF22C55E),
            warning = Color(0xFFF59E0B),
            info = Color(0xFF3B82F6),
            border = neutrals.border,
            borderFocused = accent.primary,
            ring = accent.primary.copy(alpha = 0.4f),
            inputBackground = neutrals.surface,
            inputBorder = neutrals.border,
            isLight = true,
        )
    }

    private fun toDarkColors(accent: AEAccent): AEColors {
        val neutrals = darkNeutrals()
        return AEColors(
            background = neutrals.background,
            backgroundSecondary = neutrals.backgroundSecondary,
            surface = neutrals.surface,
            surfaceHover = neutrals.surfaceHover,
            textPrimary = neutrals.textPrimary,
            textSecondary = neutrals.textSecondary,
            textMuted = neutrals.textMuted,
            textOnAccent = accent.onAccent,
            accent = accent.primary,
            accentHover = accent.hover,
            accentMuted = accent.muted.copy(alpha = 0.15f),
            destructive = Color(0xFFEF4444),
            destructiveText = Color.White,
            success = Color(0xFF22C55E),
            warning = Color(0xFFF59E0B),
            info = Color(0xFF60A5FA),
            border = neutrals.border,
            borderFocused = accent.primary,
            ring = accent.primary.copy(alpha = 0.5f),
            inputBackground = neutrals.surface,
            inputBorder = neutrals.border,
            isLight = false,
        )
    }

    // ── Neutral color sets per palette ──

    private fun lightNeutrals(): NeutralColors = when (this) {
        Zinc -> NeutralColors(
            background = Color(0xFFFFFFFF),
            backgroundSecondary = Color(0xFFFAFAFA),
            surface = Color(0xFFFFFFFF),
            surfaceHover = Color(0xFFF4F4F5),
            textPrimary = Color(0xFF09090B),
            textSecondary = Color(0xFF52525B),
            textMuted = Color(0xFFA1A1AA),
            border = Color(0xFFE4E4E7),
        )
        Slate -> NeutralColors(
            background = Color(0xFFFFFFFF),
            backgroundSecondary = Color(0xFFF8FAFC),
            surface = Color(0xFFFFFFFF),
            surfaceHover = Color(0xFFF1F5F9),
            textPrimary = Color(0xFF0F172A),
            textSecondary = Color(0xFF475569),
            textMuted = Color(0xFF94A3B8),
            border = Color(0xFFE2E8F0),
        )
        Stone -> NeutralColors(
            background = Color(0xFFFFFFFF),
            backgroundSecondary = Color(0xFFFAFAF9),
            surface = Color(0xFFFFFFFF),
            surfaceHover = Color(0xFFF5F5F4),
            textPrimary = Color(0xFF1C1917),
            textSecondary = Color(0xFF57534E),
            textMuted = Color(0xFFA8A29E),
            border = Color(0xFFE7E5E4),
        )
        Neutral -> NeutralColors(
            background = Color(0xFFFFFFFF),
            backgroundSecondary = Color(0xFFFAFAFA),
            surface = Color(0xFFFFFFFF),
            surfaceHover = Color(0xFFF5F5F5),
            textPrimary = Color(0xFF0A0A0A),
            textSecondary = Color(0xFF525252),
            textMuted = Color(0xFFA3A3A3),
            border = Color(0xFFE5E5E5),
        )
    }

    private fun darkNeutrals(): NeutralColors = when (this) {
        Zinc -> NeutralColors(
            background = Color(0xFF09090B),
            backgroundSecondary = Color(0xFF18181B),
            surface = Color(0xFF27272A),
            surfaceHover = Color(0xFF3F3F46),
            textPrimary = Color(0xFFFAFAFA),
            textSecondary = Color(0xFFA1A1AA),
            textMuted = Color(0xFF71717A),
            border = Color(0xFF3F3F46),
        )
        Slate -> NeutralColors(
            background = Color(0xFF020617),
            backgroundSecondary = Color(0xFF0F172A),
            surface = Color(0xFF1E293B),
            surfaceHover = Color(0xFF334155),
            textPrimary = Color(0xFFF8FAFC),
            textSecondary = Color(0xFF94A3B8),
            textMuted = Color(0xFF64748B),
            border = Color(0xFF334155),
        )
        Stone -> NeutralColors(
            background = Color(0xFF0C0A09),
            backgroundSecondary = Color(0xFF1C1917),
            surface = Color(0xFF292524),
            surfaceHover = Color(0xFF44403C),
            textPrimary = Color(0xFFFAFAF9),
            textSecondary = Color(0xFFA8A29E),
            textMuted = Color(0xFF78716C),
            border = Color(0xFF44403C),
        )
        Neutral -> NeutralColors(
            background = Color(0xFF0A0A0A),
            backgroundSecondary = Color(0xFF171717),
            surface = Color(0xFF262626),
            surfaceHover = Color(0xFF404040),
            textPrimary = Color(0xFFFAFAFA),
            textSecondary = Color(0xFFA3A3A3),
            textMuted = Color(0xFF737373),
            border = Color(0xFF404040),
        )
    }
}

/**
 * Internal holder for neutral grayscale colors.
 */
private data class NeutralColors(
    val background: Color,
    val backgroundSecondary: Color,
    val surface: Color,
    val surfaceHover: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val border: Color,
)
