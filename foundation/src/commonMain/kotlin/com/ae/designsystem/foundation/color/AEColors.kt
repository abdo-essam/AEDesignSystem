package com.ae.designsystem.foundation.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Semantic color roles for the AEDesignSystem.
 *
 * Colors are never referenced by raw hex in components — only through
 * these semantic roles. This enables full theme swapping (light/dark,
 * brand) without touching component code.
 */
@Immutable
public data class AEColors(
    // ── Backgrounds ──
    /** Primary page/screen background */
    val background: Color,
    /** Secondary background for layered surfaces */
    val backgroundSecondary: Color,
    /** Card/surface background */
    val surface: Color,
    /** Surface color on hover */
    val surfaceHover: Color,

    // ── Text / Content ──
    /** Primary text color */
    val textPrimary: Color,
    /** Secondary / supporting text */
    val textSecondary: Color,
    /** Muted / placeholder text */
    val textMuted: Color,
    /** Text rendered on top of accent surfaces */
    val textOnAccent: Color,

    // ── Accent / Brand ──
    /** Primary accent color (buttons, links, active states) */
    val accent: Color,
    /** Accent on hover / pressed */
    val accentHover: Color,
    /** Muted accent for subtle backgrounds */
    val accentMuted: Color,

    // ── Semantic Status ──
    /** Destructive actions (delete, error) */
    val destructive: Color,
    /** Text on destructive surfaces */
    val destructiveText: Color,
    /** Success indicator */
    val success: Color,
    /** Warning indicator */
    val warning: Color,
    /** Informational indicator */
    val info: Color,

    // ── Borders & Dividers ──
    /** Default border */
    val border: Color,
    /** Focused input border */
    val borderFocused: Color,
    /** Focus ring around interactive elements */
    val ring: Color,

    // ── Input ──
    /** Input field background */
    val inputBackground: Color,
    /** Input field border */
    val inputBorder: Color,

    /** Whether this is a light color scheme */
    val isLight: Boolean,
)
